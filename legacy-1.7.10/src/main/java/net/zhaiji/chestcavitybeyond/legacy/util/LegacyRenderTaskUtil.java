package net.zhaiji.chestcavitybeyond.legacy.util;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.zhaiji.chestcavitybeyond.legacy.network.LegacyNetwork;
import net.zhaiji.chestcavitybeyond.legacy.network.client.packet.AddGuardianLaserRenderTaskPacket;

public class LegacyRenderTaskUtil {
    private LegacyRenderTaskUtil() {
    }

    public static void sendGuardianLaserTask(EntityLivingBase attacker, EntityLivingBase target, int duration) {
        if (attacker == null || target == null || attacker.worldObj.isRemote) {
            return;
        }

        AddGuardianLaserRenderTaskPacket packet = new AddGuardianLaserRenderTaskPacket(attacker.getEntityId(), target.getEntityId(), duration);
        LegacyNetwork.CHANNEL.sendToAllAround(packet, new NetworkRegistry.TargetPoint(attacker.dimension, attacker.posX, attacker.posY, attacker.posZ, 128));
    }
}
