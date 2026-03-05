package net.zhaiji.chestcavitybeyond.legacy.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.LegacyOrganRegistry;
import net.zhaiji.chestcavitybeyond.legacy.item.ItemChestOpenerLegacy;
import net.zhaiji.chestcavitybeyond.legacy.item.organ.ItemBasicHeartOrgan;
import net.zhaiji.chestcavitybeyond.legacy.item.organ.ItemBasicLungOrgan;
import net.zhaiji.chestcavitybeyond.legacy.item.organ.ItemDamagedOrgan;
import net.zhaiji.chestcavitybeyond.legacy.organ.BasicHeartOrgan;
import net.zhaiji.chestcavitybeyond.legacy.organ.BasicLungOrgan;

public class ModItems {
    public static Item chestOpener;
    public static Item organBasicHeart;
    public static Item organBasicLung;
    public static Item organDamaged;

    public static void init() {
        chestOpener = new ItemChestOpenerLegacy();
        organBasicHeart = new ItemBasicHeartOrgan();
        organBasicLung = new ItemBasicLungOrgan();
        organDamaged = new ItemDamagedOrgan();

        GameRegistry.registerItem(chestOpener, "chest_opener");
        GameRegistry.registerItem(organBasicHeart, "organ_basic_heart");
        GameRegistry.registerItem(organBasicLung, "organ_basic_lung");
        GameRegistry.registerItem(organDamaged, "organ_damaged");

        LegacyOrganRegistry.register(organBasicHeart, new BasicHeartOrgan());
        LegacyOrganRegistry.register(organBasicLung, new BasicLungOrgan());
    }
}
