package net.zhaiji.chestcavitybeyond.api.function;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.zhaiji.chestcavitybeyond.api.ChestCavitySlotContext;

/**
 * 器官属性修饰符消费者
 */
@FunctionalInterface
public interface OrganModifierConsumer {
    /**
     * 处理器官属性修饰符
     *
     * @param context   胸腔槽位上下文
     * @param modifiers 属性修饰符映射，可向其添加属性修饰符
     */
    void accept(ChestCavitySlotContext context, Multimap<Holder<Attribute>, AttributeModifier> modifiers);
}
