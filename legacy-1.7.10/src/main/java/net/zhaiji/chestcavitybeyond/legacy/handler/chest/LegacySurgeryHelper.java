package net.zhaiji.chestcavitybeyond.legacy.handler.chest;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.init.ModItems;
import net.zhaiji.chestcavitybeyond.legacy.util.LegacyMessageUtil;

public class LegacySurgeryHelper {
    private static final String NBT_KEY_LAST_SURGERY = "cbb_last_surgery_tick";
    private static final String NBT_KEY_LAST_SURGERY_TARGET = "cbb_last_surgery_target_tick";

    private static final int PLAYER_SURGERY_COOLDOWN_TICKS = 40;
    private static final int TARGET_SURGERY_COOLDOWN_TICKS = 60;

    private LegacySurgeryHelper() {
    }

    public static boolean tryExtractOrgan(EntityPlayer player, EntityLivingBase target, ItemStack openerStack) {
        if (!target.isEntityAlive()) {
            LegacyMessageUtil.sendUnopenableMessage(player, "目标已死亡，无法进行手术");
            return false;
        }

        if (target == player) {
            LegacyMessageUtil.sendUnopenableMessage(player, "你无法给自己执行该手术");
            return false;
        }

        if (isPlayerOnCooldown(player)) {
            LegacyMessageUtil.sendUnopenableMessage(player, "手术冷却中，请稍后再试");
            return false;
        }

        if (isTargetOnCooldown(target)) {
            LegacyMessageUtil.sendUnopenableMessage(player, "目标刚接受过手术，组织仍在恢复");
            return false;
        }

        if (target.getHealth() <= 2.0F) {
            LegacyMessageUtil.sendUnopenableMessage(player, "目标过于虚弱，无法继续手术");
            return false;
        }

        ChestCavityProperties props = ChestCavityProperties.get(target);
        if (props == null) {
            return false;
        }

        ChestCavityData data = props.getData();
        if (!hasAnyOrgan(data)) {
            LegacyMessageUtil.sendUnopenableMessage(player, "目标胸腔没有可提取器官");
            return false;
        }

        setCooldown(player, target);

        int fortuneLevel = openerStack == null ? 0 : EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, openerStack);
        float successChance = getSurgerySuccessChance(player, openerStack);
        boolean success = target.worldObj.rand.nextFloat() < successChance;
        ItemStack extracted;

        if (success) {
            extracted = extractFromCavity(data);
            if (extracted == null) {
                LegacyMessageUtil.sendUnopenableMessage(player, "手术失败：未找到可提取器官");
                return false;
            }

            extracted = maybeDamageExtractedOrgan(target, extracted, fortuneLevel);
            giveToPlayer(player, extracted);

            maybeGiveRareBonusOrgan(player, target, fortuneLevel);
            LegacyMessageUtil.sendChestOpenerMessage(player, "手术成功，你提取了一个器官");
        } else {
            applyFailurePenalty(target);
            LegacyMessageUtil.sendUnopenableMessage(player, "手术失败：目标组织受损");
        }

        float damage = success ? 2.0F : 5.0F;
        target.attackEntityFrom(DamageSource.generic, damage);

        damageOpener(openerStack, player, success);
        ChestCavitySyncHandler.syncEntity(target);
        return success;
    }

    private static boolean isPlayerOnCooldown(EntityPlayer player) {
        NBTTagCompound persistent = player.getEntityData();
        long currentTick = player.worldObj.getTotalWorldTime();
        long lastTick = persistent.getLong(NBT_KEY_LAST_SURGERY);
        return currentTick - lastTick < PLAYER_SURGERY_COOLDOWN_TICKS;
    }

    private static boolean isTargetOnCooldown(EntityLivingBase target) {
        NBTTagCompound persistent = target.getEntityData();
        long currentTick = target.worldObj.getTotalWorldTime();
        long lastTick = persistent.getLong(NBT_KEY_LAST_SURGERY_TARGET);
        return currentTick - lastTick < TARGET_SURGERY_COOLDOWN_TICKS;
    }

    private static void setCooldown(EntityPlayer player, EntityLivingBase target) {
        long tick = player.worldObj.getTotalWorldTime();
        player.getEntityData().setLong(NBT_KEY_LAST_SURGERY, tick);
        target.getEntityData().setLong(NBT_KEY_LAST_SURGERY_TARGET, tick);
    }

    private static ItemStack extractFromCavity(ChestCavityData data) {
        int selected = data.selectedSlot;
        if (selected >= 0 && selected < ChestCavityData.SLOT_COUNT) {
            ItemStack selectedStack = data.getStackInSlot(selected);
            if (selectedStack != null) {
                ItemStack extracted = selectedStack.copy();
                data.setStackInSlot(selected, null);
                return extracted;
            }
        }

        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            ItemStack organ = data.getStackInSlot(i);
            if (organ != null) {
                ItemStack extracted = organ.copy();
                data.setStackInSlot(i, null);
                return extracted;
            }
        }
        return null;
    }

    private static boolean hasAnyOrgan(ChestCavityData data) {
        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            if (data.getStackInSlot(i) != null) {
                return true;
            }
        }
        return false;
    }

    private static float getSurgerySuccessChance(EntityPlayer player, ItemStack openerStack) {
        float base = 0.55F;
        if (player.experienceLevel >= 30) {
            base += 0.20F;
        } else if (player.experienceLevel >= 15) {
            base += 0.10F;
        }

        if (player.isPotionActive(Potion.digSpeed)) {
            base += 0.05F;
        }

        if (player.isPotionActive(Potion.weakness)) {
            base -= 0.10F;
        }

        if (openerStack != null) {
            int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, openerStack);
            if (fortune > 0) {
                base += 0.05F * fortune;
            }

            int efficiency = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, openerStack);
            if (efficiency > 0) {
                base += 0.02F * Math.min(efficiency, 5);
            }
        }

        if (base < 0.1F) {
            return 0.1F;
        }
        return Math.min(base, 0.95F);
    }

    private static void damageOpener(ItemStack openerStack, EntityPlayer player, boolean success) {
        if (openerStack == null) {
            return;
        }

        int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, openerStack);
        boolean consumeDurability = player.getRNG().nextInt(unbreakingLevel + 1) == 0;
        if (consumeDurability) {
            openerStack.damageItem(success ? 1 : 2, player);
        }
    }

    private static void applyFailurePenalty(EntityLivingBase target) {
        target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
        target.addPotionEffect(new PotionEffect(Potion.weakness.id, 60, 0));
    }

    private static ItemStack maybeDamageExtractedOrgan(EntityLivingBase target, ItemStack extracted, int fortuneLevel) {
        float damageChance = 0.25F - 0.05F * fortuneLevel;
        if (damageChance < 0.05F) {
            damageChance = 0.05F;
        }

        if (target.worldObj.rand.nextFloat() < damageChance) {
            return new ItemStack(ModItems.organDamaged);
        }
        return extracted;
    }

    private static void maybeGiveRareBonusOrgan(EntityPlayer player, EntityLivingBase target, int fortuneLevel) {
        float rareChance = (target instanceof IMob ? 0.10F : 0.03F) + 0.02F * fortuneLevel;
        if (target.worldObj.rand.nextFloat() < rareChance) {
            ItemStack rareBonus = new ItemStack(target.worldObj.rand.nextBoolean() ? ModItems.organBasicHeart : ModItems.organBasicLung);
            giveToPlayer(player, rareBonus);
            LegacyMessageUtil.sendChestOpenerMessage(player, "你额外获得了一个稀有器官");
        }
    }

    private static void giveToPlayer(EntityPlayer player, ItemStack stack) {
        if (stack == null) {
            return;
        }

        if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropPlayerItemWithRandomChoice(stack, false);
        }
    }

}
