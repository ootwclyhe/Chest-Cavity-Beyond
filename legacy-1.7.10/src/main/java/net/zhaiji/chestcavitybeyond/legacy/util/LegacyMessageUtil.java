package net.zhaiji.chestcavitybeyond.legacy.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.zhaiji.chestcavitybeyond.legacy.network.LegacyNetwork;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.ChestOpenerMessagePacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.UnopenableChestCavityMessagePacket;

public class LegacyMessageUtil {
    private LegacyMessageUtil() {
    }

    public static void sendChestOpenerMessage(EntityPlayer player, String message) {
        if (player instanceof EntityPlayerMP) {
            LegacyNetwork.CHANNEL.sendTo(new ChestOpenerMessagePacket(message), (EntityPlayerMP) player);
        }
    }

    public static void sendUnopenableMessage(EntityPlayer player, String message) {
        if (player instanceof EntityPlayerMP) {
            LegacyNetwork.CHANNEL.sendTo(new UnopenableChestCavityMessagePacket(message), (EntityPlayerMP) player);
        }
    }
}
