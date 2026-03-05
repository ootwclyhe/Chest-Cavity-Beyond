package net.zhaiji.chestcavitybeyond.legacy.handler.chest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;

public class LegacyOrganDropHandler {
    public static void register() {
        MinecraftForge.EVENT_BUS.register(new LegacyOrganDropHandler());
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        ChestCavityProperties props = ChestCavityProperties.get(entity);
        if (props == null) {
            return;
        }

        ChestCavityData data = props.getData();
        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            ItemStack organ = data.getStackInSlot(i);
            if (organ != null) {
                event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, organ.copy()));
                data.setStackInSlot(i, null);
            }
        }
    }
}
