package net.zhaiji.chestcavitybeyond.legacy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.zhaiji.chestcavitybeyond.legacy.handler.EntityLifecycleHandler;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.ChestCavitySyncHandler;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.LegacyChestCavityInitHandler;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.LegacyOrganDropHandler;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.LegacyOrganTickHandler;
import net.zhaiji.chestcavitybeyond.legacy.handler.gui.LegacyGuiHandler;
import net.zhaiji.chestcavitybeyond.legacy.init.ModItems;
import net.zhaiji.chestcavitybeyond.legacy.init.ModRecipes;
import net.zhaiji.chestcavitybeyond.legacy.network.LegacyNetwork;
import net.zhaiji.chestcavitybeyond.legacy.proxy.CommonProxy;

@Mod(modid = ChestCavityBeyondLegacy.MOD_ID, name = ChestCavityBeyondLegacy.MOD_NAME, version = ChestCavityBeyondLegacy.VERSION)
public class ChestCavityBeyondLegacy {
    public static final String MOD_ID = "chestcavitybeyond";
    public static final String MOD_NAME = "Chest Cavity Beyond";
    public static final String VERSION = "1.0.5-legacy-1.7.10";

    @Mod.Instance(MOD_ID)
    public static ChestCavityBeyondLegacy INSTANCE;

    @SidedProxy(clientSide = "net.zhaiji.chestcavitybeyond.legacy.proxy.ClientProxy", serverSide = "net.zhaiji.chestcavitybeyond.legacy.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        LegacyNetwork.init();
        EntityLifecycleHandler.register();
        ChestCavitySyncHandler.register();
        LegacyChestCavityInitHandler.register();
        LegacyOrganTickHandler.register();
        LegacyOrganDropHandler.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new LegacyGuiHandler());
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
