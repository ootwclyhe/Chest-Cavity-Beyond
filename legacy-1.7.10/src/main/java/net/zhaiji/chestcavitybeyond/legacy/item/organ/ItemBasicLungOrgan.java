package net.zhaiji.chestcavitybeyond.legacy.item.organ;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBasicLungOrgan extends Item {
    public ItemBasicLungOrgan() {
        setUnlocalizedName("organ_basic_lung");
        setTextureName("chestcavitybeyond:organ_basic_lung");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }
}
