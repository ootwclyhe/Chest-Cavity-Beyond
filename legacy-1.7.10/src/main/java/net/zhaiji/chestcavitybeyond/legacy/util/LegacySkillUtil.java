package net.zhaiji.chestcavitybeyond.legacy.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.ILegacyOrgan;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.LegacyOrganRegistry;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;

public class LegacySkillUtil {
    private static final String NBT_KEY_SKILL_COOLDOWN_PREFIX = "cbb_skill_cd_";

    private LegacySkillUtil() {
    }

    public static void useSkill(EntityPlayer player, int slot) {
        ChestCavityProperties props = ChestCavityProperties.get(player);
        if (props == null) {
            return;
        }

        ChestCavityData data = props.getData();
        int useSlot = slot;
        if (useSlot < 0) {
            useSlot = data.selectedSlot;
        }
        if (useSlot < 0 || useSlot >= ChestCavityData.SLOT_COUNT) {
            return;
        }

        ItemStack stack = data.getStackInSlot(useSlot);
        if (stack == null) {
            return;
        }

        ILegacyOrgan organ = LegacyOrganRegistry.get(stack.getItem());
        if (organ == null || !organ.hasSkill()) {
            return;
        }

        int cooldownTicks = organ.getSkillCooldownTicks();
        if (cooldownTicks > 0) {
            long now = player.worldObj.getTotalWorldTime();
            String key = getSkillCooldownKey(organ);
            long lastUse = player.getEntityData().getLong(key);
            long remain = cooldownTicks - (now - lastUse);
            if (remain > 0) {
                LegacyMessageUtil.sendUnopenableMessage(player, "技能冷却中，还需 " + remain + " tick");
                return;
            }
            player.getEntityData().setLong(key, now);
        }

        organ.onUse(player, stack, useSlot);
    }

    private static String getSkillCooldownKey(ILegacyOrgan organ) {
        return NBT_KEY_SKILL_COOLDOWN_PREFIX + organ.getId();
    }
}
