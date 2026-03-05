package net.zhaiji.chestcavitybeyond.legacy.chestcavity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.handler.chest.ChestCavitySyncHandler;

public class InventoryChestCavity implements IInventory {
    private final EntityLivingBase owner;
    private final ChestCavityData data;

    public InventoryChestCavity(EntityLivingBase owner) {
        this.owner = owner;
        ChestCavityProperties props = ChestCavityProperties.get(owner);
        this.data = props == null ? new ChestCavityData() : props.getData();
    }

    @Override
    public int getSizeInventory() {
        return ChestCavityData.SLOT_COUNT;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return data.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if (stack == null) {
            return null;
        }

        if (stack.stackSize <= count) {
            setInventorySlotContents(index, null);
            return stack;
        }

        ItemStack split = stack.splitStack(count);
        if (stack.stackSize <= 0) {
            setInventorySlotContents(index, null);
        } else {
            setInventorySlotContents(index, stack);
        }
        return split;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        data.setStackInSlot(index, stack);
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "inventory.chestcavitybeyond.chestcavity";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void markDirty() {
        if (!owner.worldObj.isRemote) {
            ChestCavitySyncHandler.syncEntity(owner);
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player == owner || player.getDistanceSqToEntity(owner) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
}
