package net.zhaiji.chestcavitybeyond.legacy.chestcavity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ChestCavityProperties implements IExtendedEntityProperties {
    public static final String KEY = "ChestCavityBeyondData";

    private final ChestCavityData data = new ChestCavityData();

    public static void register(EntityLivingBase entity) {
        if (entity.getExtendedProperties(KEY) == null) {
            entity.registerExtendedProperties(KEY, new ChestCavityProperties());
        }
    }

    public static ChestCavityProperties get(EntityLivingBase entity) {
        return (ChestCavityProperties) entity.getExtendedProperties(KEY);
    }

    public ChestCavityData getData() {
        return data;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setTag(KEY, data.toNbt());
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if (compound.hasKey(KEY, 10)) {
            data.fromNbt(compound.getCompoundTag(KEY));
        }
    }

    @Override
    public void init(Entity entity, World world) {
    }
}
