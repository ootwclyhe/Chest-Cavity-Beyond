package net.zhaiji.chestcavitybeyond.api;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * 属性条目，用于存储预定义的属性修饰符
 */
public record AttributeEntry(Holder<Attribute> attribute, double value, AttributeModifier.Operation operation) {
}
