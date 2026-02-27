package net.zhaiji.chestcavitybeyond.legacy.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.zhaiji.chestcavitybeyond.legacy.ChestCavityBeyondLegacy;

public class LegacyNetwork {
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(ChestCavityBeyondLegacy.MOD_ID);

    public static void init() {
        CHANNEL.registerMessage(SyncChestCavityPacket.Handler.class, SyncChestCavityPacket.class, 0, Side.CLIENT);
    }
}
