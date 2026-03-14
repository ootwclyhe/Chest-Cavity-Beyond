package net.zhaiji.chestcavitybeyond.legacy.handler.chest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.ILegacyOrgan;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.LegacyOrganRegistry;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;

public class LegacyOrganTickHandler {
    public static void register() {
        MinecraftForge.EVENT_BUS.register(new LegacyOrganTickHandler());
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase living = event.entityLiving;
        ChestCavityProperties props = ChestCavityProperties.get(living);
        if (props == null) {
            return;
        }

        ChestCavityData data = props.getData();
        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            ItemStack organStack = data.getStackInSlot(i);
            if (organStack == null) {
                continue;
            }

            ILegacyOrgan organ = LegacyOrganRegistry.get(organStack.getItem());
            if (organ != null) {
                organ.onTick(living, organStack, i);
            }
        }
    }
}
