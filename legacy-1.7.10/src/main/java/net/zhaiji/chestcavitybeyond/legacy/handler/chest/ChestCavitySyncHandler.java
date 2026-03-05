package net.zhaiji.chestcavitybeyond.legacy.handler.chest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.network.LegacyNetwork;
import net.zhaiji.chestcavitybeyond.legacy.network.SyncChestCavityPacket;

public class ChestCavitySyncHandler {
    public static void register() {
        MinecraftForge.EVENT_BUS.register(new ChestCavitySyncHandler());
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayerMP) {
            syncEntity((EntityPlayerMP) event.entity);
        }
    }

    @SubscribeEvent
    public void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.entityPlayer instanceof EntityPlayerMP) || !(event.target instanceof EntityLivingBase)) {
            return;
        }

        EntityPlayerMP watcher = (EntityPlayerMP) event.entityPlayer;
        EntityLivingBase target = (EntityLivingBase) event.target;
        ChestCavityProperties props = ChestCavityProperties.get(target);
        if (props != null) {
            LegacyNetwork.CHANNEL.sendTo(new SyncChestCavityPacket(target.getEntityId(), props.getData().toNbt()), watcher);
        }
    }

    public static void syncEntity(EntityLivingBase entity) {
        if (entity.worldObj.isRemote) {
            return;
        }

        ChestCavityProperties props = ChestCavityProperties.get(entity);
        if (props == null) {
            return;
        }

        SyncChestCavityPacket packet = new SyncChestCavityPacket(entity.getEntityId(), props.getData().toNbt());
        LegacyNetwork.CHANNEL.sendToAllAround(
                packet,
                new NetworkRegistry.TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 128)
        );

        if (entity instanceof EntityPlayerMP) {
            LegacyNetwork.CHANNEL.sendTo(packet, (EntityPlayerMP) entity);
        }
    }

    public static void syncPlayer(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            syncEntity((EntityPlayerMP) player);
        }
    }
}
