package net.zhaiji.chestcavitybeyond.legacy.handler.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.zhaiji.chestcavitybeyond.legacy.client.state.LegacyRenderTaskState;

public class LegacyClientTickHandler {
    private LegacyClientTickHandler() {
    }

    public static void register() {
        FMLCommonHandler.instance().bus().register(new LegacyClientTickHandler());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().theWorld != null) {
            LegacyRenderTaskState.tick();
        }
    }
}
