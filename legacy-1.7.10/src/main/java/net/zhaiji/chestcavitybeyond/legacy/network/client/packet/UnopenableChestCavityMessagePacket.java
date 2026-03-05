package net.zhaiji.chestcavitybeyond.legacy.network.client.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class UnopenableChestCavityMessagePacket implements IMessage {
    private String message;

    public UnopenableChestCavityMessagePacket() {
    }

    public UnopenableChestCavityMessagePacket(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.message = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, message);
    }
}
