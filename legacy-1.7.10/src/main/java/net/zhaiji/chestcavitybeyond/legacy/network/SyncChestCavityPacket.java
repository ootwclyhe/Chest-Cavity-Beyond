package net.zhaiji.chestcavitybeyond.legacy.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.ChestCavityProperties;
import net.zhaiji.chestcavitybeyond.legacy.client.state.LegacyClientState;

public class SyncChestCavityPacket implements IMessage {
    private int entityId;
    private NBTTagCompound data;

    public SyncChestCavityPacket() {
    }

    public SyncChestCavityPacket(int entityId, NBTTagCompound data) {
        this.entityId = entityId;
        this.data = data;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityId);
        ByteBufUtils.writeTag(buf, data);
    }

    public static class Handler implements IMessageHandler<SyncChestCavityPacket, IMessage> {
        @Override
        public IMessage onMessage(SyncChestCavityPacket message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc == null || mc.theWorld == null) {
                return null;
            }

            Entity entity = mc.theWorld.getEntityByID(message.entityId);
            if (entity instanceof EntityLivingBase) {
                ChestCavityProperties props = ChestCavityProperties.get((EntityLivingBase) entity);
                if (props != null && message.data != null) {
                    props.getData().fromNbt(message.data);
                    if (entity == mc.thePlayer) {
                        LegacyClientState.selectedSlot = props.getData().selectedSlot;
                    }
                }
            }
            return null;
        }
    }
}
