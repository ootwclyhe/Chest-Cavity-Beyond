package net.zhaiji.chestcavitybeyond.legacy.handler.chest;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.init.ModItems;

public class LegacySurgeryHelper {
    private LegacySurgeryHelper() {
    }

    public static boolean tryExtractOrgan(EntityPlayer player, EntityLivingBase target) {
        if (target.getHealth() <= 2.0F) {
            player.addChatMessage(new ChatComponentText("目标过于虚弱，无法继续手术"));
            return false;
        }

        ChestCavityProperties props = ChestCavityProperties.get(target);
        if (props == null) {
            return false;
        }

        ChestCavityData data = props.getData();
        ItemStack extracted = null;
        int extractedSlot = -1;

        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            ItemStack organ = data.getStackInSlot(i);
            if (organ != null) {
                extracted = organ.copy();
                extractedSlot = i;
                break;
            }
        }

        if (extracted == null) {
            extracted = generateFallbackOrgan(target);
        } else {
            data.setStackInSlot(extractedSlot, null);
        }

        if (!player.inventory.addItemStackToInventory(extracted)) {
            player.dropPlayerItemWithRandomChoice(extracted, false);
        }

        target.attackEntityFrom(net.minecraft.util.DamageSource.generic, 2.0F);
        ChestCavitySyncHandler.syncEntity(target);
        player.addChatMessage(new ChatComponentText("你提取了一个器官"));
        return true;
    }

    private static ItemStack generateFallbackOrgan(EntityLivingBase target) {
        if (target.isInWater()) {
            return new ItemStack(ModItems.organBasicLung);
        }
        if (target instanceof IMob) {
            return new ItemStack(ModItems.organBasicHeart);
        }
        return new ItemStack(target.worldObj.rand.nextBoolean() ? ModItems.organBasicHeart : ModItems.organBasicLung);
    }
}
