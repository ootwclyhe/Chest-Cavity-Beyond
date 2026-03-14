package net.zhaiji.chestcavitybeyond.legacy.proxy;

import net.zhaiji.chestcavitybeyond.legacy.client.key.LegacyKeyBindings;
import net.zhaiji.chestcavitybeyond.legacy.handler.client.LegacyClientInputHandler;
import net.zhaiji.chestcavitybeyond.legacy.handler.client.LegacyClientTickHandler;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        LegacyKeyBindings.register();
        LegacyClientInputHandler.register();
        LegacyClientTickHandler.register();
    }
}
