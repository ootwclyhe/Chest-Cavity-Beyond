package net.zhaiji.chestcavitybeyond.legacy.item.organ;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBasicHeartOrgan extends Item {
    public ItemBasicHeartOrgan() {
        setUnlocalizedName("organ_basic_heart");
        setTextureName("chestcavitybeyond:organ_basic_heart");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }
}
