package net.zhaiji.chestcavitybeyond.legacy.organ;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.ILegacyOrgan;

public class BasicHeartOrgan implements ILegacyOrgan {
    @Override
    public String getId() {
        return "basic_heart";
    }

    @Override
    public void onTick(EntityLivingBase entity, ItemStack stack, int slotIndex) {
        if (entity.ticksExisted % 100 == 0 && entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(0.5F);
        }
    }
    @Override
    public void onUse(EntityLivingBase entity, ItemStack stack, int slotIndex) {
        onTick(entity, stack, slotIndex);
    }

}
