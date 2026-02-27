package net.zhaiji.chestcavitybeyond.legacy.organ;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.ILegacyOrgan;

public class BasicLungOrgan implements ILegacyOrgan {
    @Override
    public String getId() {
        return "basic_lung";
    }

    @Override
    public void onTick(EntityLivingBase entity, ItemStack stack, int slotIndex) {
        if (entity.ticksExisted % 40 == 0) {
            entity.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 60, 0));
        }
    }
}
