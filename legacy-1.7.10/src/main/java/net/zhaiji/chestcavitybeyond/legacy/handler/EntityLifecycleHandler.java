package net.zhaiji.chestcavitybeyond.legacy.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;

public class EntityLifecycleHandler {
    public static void register() {
        EntityLifecycleHandler handler = new EntityLifecycleHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase) {
            ChestCavityProperties.register((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent
    public void onClone(PlayerEvent.Clone event) {
        ChestCavityProperties source = ChestCavityProperties.get(event.original);
        ChestCavityProperties target = ChestCavityProperties.get(event.entityPlayer);
        if (source != null && target != null) {
            target.getData().fromNbt(source.getData().toNbt());
        }
    }
}
