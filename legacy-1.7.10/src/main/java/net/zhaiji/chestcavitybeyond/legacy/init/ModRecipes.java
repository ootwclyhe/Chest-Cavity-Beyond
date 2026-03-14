package net.zhaiji.chestcavitybeyond.legacy.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {
    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ModItems.chestOpener), " I ", "LSL", " I ", 'I', Items.iron_ingot, 'L', Items.lever, 'S', Items.stick);
    }
}
