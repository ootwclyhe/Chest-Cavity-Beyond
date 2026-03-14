package net.zhaiji.chestcavitybeyond.legacy.network.server.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class UseSkillPacket implements IMessage {
    private int slot;

    public UseSkillPacket() {
    }

    public UseSkillPacket(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slot = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(slot);
    }
}
