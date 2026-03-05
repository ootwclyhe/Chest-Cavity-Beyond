package net.zhaiji.chestcavitybeyond.legacy.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.ILegacyOrgan;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.LegacyOrganRegistry;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;

public class LegacySkillUtil {
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
        if (organ != null) {
            organ.onUse(player, stack, useSlot);
        }
    }
}
