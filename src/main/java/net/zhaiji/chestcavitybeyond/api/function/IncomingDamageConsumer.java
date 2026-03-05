package net.zhaiji.chestcavitybeyond.api.function;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.zhaiji.chestcavitybeyond.api.ChestCavitySlotContext;

/**
 * 器官拥有者受到伤害前
 */
@FunctionalInterface
public interface IncomingDamageConsumer {
    /**
     * @param context 胸腔槽位上下文
     * @param event   伤害事件
     */
    void accept(ChestCavitySlotContext context, LivingIncomingDamageEvent event);
}
