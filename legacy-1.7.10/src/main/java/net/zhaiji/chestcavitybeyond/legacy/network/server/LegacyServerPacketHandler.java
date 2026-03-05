package net.zhaiji.chestcavitybeyond.legacy.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityData;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.ChestCavitySyncHandler;
import net.zhaiji.chestcavitybeyond.legacy.network.LegacyNetwork;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.SyncSelectedSlotClientPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.server.packet.SyncSelectedSlotPacket;
import net.zhaiji.chestcavitybeyond.legacy.network.server.packet.UseSkillPacket;
import net.zhaiji.chestcavitybeyond.legacy.util.LegacySkillUtil;

public class LegacyServerPacketHandler {
    public static class UseSkillHandler implements IMessageHandler<UseSkillPacket, IMessage> {
        @Override
        public IMessage onMessage(UseSkillPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player != null) {
                LegacySkillUtil.useSkill(player, message.getSlot());
            }
            return null;
        }
    }

    public static class SyncSelectedSlotHandler implements IMessageHandler<SyncSelectedSlotPacket, IMessage> {
        @Override
        public IMessage onMessage(SyncSelectedSlotPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player != null) {
                ChestCavityProperties props = ChestCavityProperties.get(player);
                if (props != null) {
                    int slot = message.getSlot();
                    if (slot >= -1 && slot < ChestCavityData.SLOT_COUNT) {
                        props.getData().selectedSlot = slot;
                        ChestCavitySyncHandler.syncEntity(player);
                        LegacyNetwork.CHANNEL.sendTo(new SyncSelectedSlotClientPacket(slot), player);
                    }
                }
            }
            return null;
        }
    }
}
