package net.zhaiji.chestcavitybeyond.legacy.handler.chest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.init.ModItems;

public class LegacyChestCavityInitHandler {
    public static void register() {
        MinecraftForge.EVENT_BUS.register(new LegacyChestCavityInitHandler());
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.world.isRemote || !(event.entity instanceof EntityLivingBase)) {
            return;
        }

        EntityLivingBase living = (EntityLivingBase) event.entity;
        ChestCavityProperties props = ChestCavityProperties.get(living);
        if (props == null) {
            return;
        }

        ChestCavityData data = props.getData();
        if (hasAnyOrgan(data)) {
            return;
        }

        seedDefaultOrgans(living, data);
        ChestCavitySyncHandler.syncEntity(living);
    }

    private static boolean hasAnyOrgan(ChestCavityData data) {
        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            if (data.getStackInSlot(i) != null) {
                return true;
            }
        }
        return false;
    }

    private static void seedDefaultOrgans(EntityLivingBase living, ChestCavityData data) {
        data.setStackInSlot(0, new ItemStack(ModItems.organBasicHeart));

        if (living instanceof EntityPlayer) {
            data.setStackInSlot(1, new ItemStack(ModItems.organBasicLung));
            data.setStackInSlot(2, new ItemStack(ModItems.organBasicLung));
            return;
        }

        if (living instanceof EntityAnimal) {
            data.setStackInSlot(1, new ItemStack(ModItems.organBasicLung));
            return;
        }

        if (living instanceof IMob) {
            if (living.isInWater()) {
                data.setStackInSlot(1, new ItemStack(ModItems.organBasicLung));
            }
            return;
        }

        data.setStackInSlot(1, new ItemStack(ModItems.organBasicLung));
    }
}
