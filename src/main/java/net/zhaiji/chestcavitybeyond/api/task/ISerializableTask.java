package net.zhaiji.chestcavitybeyond.api.task;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

/**
 * 可序列化的胸腔任务接口
 * 实现此接口的task可以被序列化保存到NBT
 */
public interface ISerializableTask extends IChestCavityTask {
    /**
     * 获取Task类型，用于序列化/反序列化
     * @return Task类型，格式应为 "modid:task_name"
     */
    ResourceLocation getType();

    /**
     * 序列化任务状态到NBT
     * @param provider HolderLookup.Provider
     * @return 包含任务状态的CompoundTag
     */
    CompoundTag serializeNBT(HolderLookup.Provider provider);
}
