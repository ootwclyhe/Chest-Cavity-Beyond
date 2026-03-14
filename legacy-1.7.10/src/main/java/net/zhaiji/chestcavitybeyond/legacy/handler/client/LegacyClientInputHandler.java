package net.zhaiji.chestcavitybeyond.legacy.handler.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.ILegacyOrgan;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.LegacyOrganRegistry;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.client.key.LegacyKeyBindings;
import net.zhaiji.chestcavitybeyond.legacy.client.state.LegacyClientState;
import net.zhaiji.chestcavitybeyond.legacy.network.LegacyNetwork;
import net.zhaiji.chestcavitybeyond.legacy.network.server.packet.SyncSelectedSlotPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.server.packet.UseSkillPacket;

public class LegacyClientInputHandler {
    private LegacyClientInputHandler() {
    }

    public static void register() {
        FMLCommonHandler.instance().bus().register(new LegacyClientInputHandler());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.thePlayer;
        if (player == null) {
            return;
        }

        while (LegacyKeyBindings.NEXT_ORGAN_SKILL.isPressed()) {
            selectNext(player, 1);
        }
        while (LegacyKeyBindings.PREV_ORGAN_SKILL.isPressed()) {
            selectNext(player, -1);
        }
        while (LegacyKeyBindings.USE_ORGAN_SKILL.isPressed()) {
            useSelected(player);
        }
    }

    private static void selectNext(EntityPlayer player, int step) {
        ChestCavityProperties props = ChestCavityProperties.get(player);
        if (props == null) {
            return;
        }

        ChestCavityData data = props.getData();
        int start = LegacyClientState.selectedSlot;
        if (!isSkillSlot(data, start)) {
            start = data.selectedSlot;
        }

        int found = findSkillSlot(data, start, step);
        if (found >= 0) {
            LegacyClientState.selectedSlot = found;
            LegacyNetwork.CHANNEL.sendToServer(new SyncSelectedSlotPacket(found));
        }
    }

    private static void useSelected(EntityPlayer player) {
        ChestCavityProperties props = ChestCavityProperties.get(player);
        if (props == null) {
            return;
        }

        ChestCavityData data = props.getData();
        int slot = LegacyClientState.selectedSlot;
        if (!isSkillSlot(data, slot)) {
            slot = data.selectedSlot;
        }
        if (!isSkillSlot(data, slot)) {
            slot = findSkillSlot(data, -1, 1);
        }
        if (slot < 0) {
            return;
        }

        LegacyClientState.selectedSlot = slot;
        LegacyNetwork.CHANNEL.sendToServer(new SyncSelectedSlotPacket(slot));
        LegacyNetwork.CHANNEL.sendToServer(new UseSkillPacket(slot));
    }

    private static int findSkillSlot(ChestCavityData data, int currentSlot, int step) {
        for (int i = 0; i < ChestCavityData.SLOT_COUNT; i++) {
            int index = (currentSlot + step * (i + 1)) % ChestCavityData.SLOT_COUNT;
            if (index < 0) {
                index += ChestCavityData.SLOT_COUNT;
            }
            if (isSkillSlot(data, index)) {
                return index;
            }
        }
        return -1;
    }

    private static boolean isSkillSlot(ChestCavityData data, int slot) {
        if (slot < 0 || slot >= ChestCavityData.SLOT_COUNT) {
            return false;
        }

        ItemStack stack = data.getStackInSlot(slot);
        if (stack == null) {
            return false;
        }

        ILegacyOrgan organ = LegacyOrganRegistry.get(stack.getItem());
        return organ != null && organ.hasSkill();
    }
}
