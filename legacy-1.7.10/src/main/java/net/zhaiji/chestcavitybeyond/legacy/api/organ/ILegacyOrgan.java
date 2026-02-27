package net.zhaiji.chestcavitybeyond.legacy.api.organ;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface ILegacyOrgan {
    String getId();

    void onTick(EntityLivingBase entity, ItemStack stack, int slotIndex);
}
