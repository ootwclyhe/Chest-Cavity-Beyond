package net.zhaiji.chestcavitybeyond.legacy.item.organ;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemDamagedOrgan extends Item {
    public ItemDamagedOrgan() {
        setUnlocalizedName("organ_damaged");
        setTextureName("chestcavitybeyond:organ_damaged");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(16);
    }
}
