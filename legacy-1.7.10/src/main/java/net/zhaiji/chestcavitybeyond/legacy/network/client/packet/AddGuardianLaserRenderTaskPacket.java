package net.zhaiji.chestcavitybeyond.legacy.network.client.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class AddGuardianLaserRenderTaskPacket implements IMessage {
    private int attackerId;
    private int targetId;
    private int duration;

    public AddGuardianLaserRenderTaskPacket() {
    }

    public AddGuardianLaserRenderTaskPacket(int attackerId, int targetId, int duration) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.duration = duration;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.attackerId = buf.readInt();
        this.targetId = buf.readInt();
        this.duration = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(attackerId);
        buf.writeInt(targetId);
        buf.writeInt(duration);
    }
}
