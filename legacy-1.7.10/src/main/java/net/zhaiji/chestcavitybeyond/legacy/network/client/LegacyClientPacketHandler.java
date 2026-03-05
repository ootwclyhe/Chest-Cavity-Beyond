package net.zhaiji.chestcavitybeyond.legacy.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.zhaiji.chestcavitybeyond.legacy.client.state.LegacyClientState;
import net.zhaiji.chestcavitybeyond.legacy.client.state.LegacyRenderTaskState;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.AddGuardianLaserRenderTaskPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.ChestOpenerMessagePacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.SyncSelectedSlotClientPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.UnopenableChestCavityMessagePacket;

public class LegacyClientPacketHandler {
    public static class ChestOpenerMessageHandler implements IMessageHandler<ChestOpenerMessagePacket, IMessage> {
        @Override
        public IMessage onMessage(ChestOpenerMessagePacket message, MessageContext ctx) {
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message.getMessage()));
            }
            return null;
        }
    }

    public static class UnopenableMessageHandler implements IMessageHandler<UnopenableChestCavityMessagePacket, IMessage> {
        @Override
        public IMessage onMessage(UnopenableChestCavityMessagePacket message, MessageContext ctx) {
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message.getMessage()));
            }
            return null;
        }
    }

    public static class AddGuardianLaserRenderTaskHandler implements IMessageHandler<AddGuardianLaserRenderTaskPacket, IMessage> {
        @Override
        public IMessage onMessage(AddGuardianLaserRenderTaskPacket message, MessageContext ctx) {
            LegacyRenderTaskState.addGuardianLaserTask(message.getAttackerId(), message.getTargetId(), message.getDuration());
            return null;
        }
    }

    public static class SyncSelectedSlotClientHandler implements IMessageHandler<SyncSelectedSlotClientPacket, IMessage> {
        @Override
        public IMessage onMessage(SyncSelectedSlotClientPacket message, MessageContext ctx) {
            LegacyClientState.selectedSlot = message.getSlot();
            return null;
        }
    }
}
