package net.zhaiji.chestcavitybeyond.legacy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.zhaiji.chestcavitybeyond.legacy.ChestCavityBeyondLegacy;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.LegacySurgeryHelper;
import net.zhaiji.chestcavitybeyond.legacy.handler.gui.LegacyGuiHandler;

public class ItemChestOpenerLegacy extends Item {
    public ItemChestOpenerLegacy() {
        setUnlocalizedName("chest_opener");
        setTextureName("chestcavitybeyond:chest_opener");
        setCreativeTab(CreativeTabs.tabTools);
        setMaxStackSize(1);
        setMaxDamage(128);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.openGui(ChestCavityBeyondLegacy.INSTANCE, LegacyGuiHandler.CHEST_CAVITY_GUI, world, player.getEntityId(), 0, 0);
        }
        return stack;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        if (!player.worldObj.isRemote) {
            if (player.isSneaking()) {
                return LegacySurgeryHelper.tryExtractOrgan(player, target, stack);
            }
            player.openGui(ChestCavityBeyondLegacy.INSTANCE, LegacyGuiHandler.CHEST_CAVITY_GUI, player.worldObj, target.getEntityId(), 0, 0);
        }
        return true;
    }
}
