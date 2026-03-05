package net.zhaiji.chestcavitybeyond.legacy.handler.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.zhaiji.chestcavitybeyond.legacy.client.gui.GuiChestCavityLegacy;
import net.zhaiji.chestcavitybeyond.legacy.container.ContainerChestCavityLegacy;

public class LegacyGuiHandler implements IGuiHandler {
    public static final int CHEST_CAVITY_GUI = 1;

    private EntityLivingBase resolveTarget(EntityPlayer player, World world, int entityId) {
        Entity entity = world.getEntityByID(entityId);
        if (entity instanceof EntityLivingBase) {
            return (EntityLivingBase) entity;
        }
        return player;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == CHEST_CAVITY_GUI) {
            return new ContainerChestCavityLegacy(player.inventory, resolveTarget(player, world, x));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == CHEST_CAVITY_GUI) {
            return new GuiChestCavityLegacy(player.inventory, resolveTarget(player, world, x));
        }
        return null;
    }
}
