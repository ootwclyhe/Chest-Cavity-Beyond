package net.zhaiji.chestcavitybeyond.legacy.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.zhaiji.chestcavitybeyond.legacy.ChestCavityBeyondLegacy;
import net.zhaiji.chestcavitybeyond.legacy.network.client.LegacyClientPacketHandler;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.AddGuardianLaserRenderTaskPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.ChestOpenerMessagePacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.SyncSelectedSlotClientPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.UnopenableChestCavityMessagePacket;
import net.zhaiji.chestcavitybeyond.legacy.network.server.LegacyServerPacketHandler;
import net.zhaiji.chestcavitybeyond.legacy.network.server.packet.SyncSelectedSlotPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.server.packet.UseSkillPacket;

public class LegacyNetwork {
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(ChestCavityBeyondLegacy.MOD_ID);

    public static void init() {
        CHANNEL.registerMessage(SyncChestCavityPacket.Handler.class, SyncChestCavityPacket.class, 0, Side.CLIENT);
        CHANNEL.registerMessage(LegacyServerPacketHandler.UseSkillHandler.class, UseSkillPacket.class, 1, Side.SERVER);
        CHANNEL.registerMessage(LegacyServerPacketHandler.SyncSelectedSlotHandler.class, SyncSelectedSlotPacket.class, 2, Side.SERVER);
        CHANNEL.registerMessage(LegacyClientPacketHandler.ChestOpenerMessageHandler.class, ChestOpenerMessagePacket.class, 3, Side.CLIENT);
        CHANNEL.registerMessage(LegacyClientPacketHandler.UnopenableMessageHandler.class, UnopenableChestCavityMessagePacket.class, 4, Side.CLIENT);
        CHANNEL.registerMessage(LegacyClientPacketHandler.SyncSelectedSlotClientHandler.class, SyncSelectedSlotClientPacket.class, 5, Side.CLIENT);
        CHANNEL.registerMessage(LegacyClientPacketHandler.AddGuardianLaserRenderTaskHandler.class, AddGuardianLaserRenderTaskPacket.class, 6, Side.CLIENT);
    }
}
