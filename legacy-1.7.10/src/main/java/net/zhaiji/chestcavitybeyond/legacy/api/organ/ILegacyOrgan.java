package net.zhaiji.chestcavitybeyond.legacy.api.organ;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface ILegacyOrgan {
    String getId();

    default boolean hasSkill() {
        return false;
    }

    default int getSkillCooldownTicks() {
        return 0;
    }

    void onTick(EntityLivingBase entity, ItemStack stack, int slotIndex);

    void onUse(EntityLivingBase entity, ItemStack stack, int slotIndex);
}
