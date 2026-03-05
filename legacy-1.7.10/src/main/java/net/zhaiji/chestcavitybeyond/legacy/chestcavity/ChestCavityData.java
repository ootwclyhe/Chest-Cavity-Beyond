package net.zhaiji.chestcavitybeyond.legacy.chestcavity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ChestCavityData {
    public int selectedSlot = -1;
    public static final int ROWS = 3;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;

    private final ItemStack[] organs = new ItemStack[SLOT_COUNT];

    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= SLOT_COUNT) {
            return null;
        }
        return organs[index];
    }

    public void setStackInSlot(int index, ItemStack stack) {
        if (index < 0 || index >= SLOT_COUNT) {
            return;
        }
        organs[index] = stack;
    }

    public NBTTagCompound toNbt() {
        NBTTagCompound root = new NBTTagCompound();
        NBTTagList organList = new NBTTagList();
        for (int i = 0; i < SLOT_COUNT; i++) {
            ItemStack stack = organs[i];
            if (stack != null) {
                NBTTagCompound entry = new NBTTagCompound();
                entry.setInteger("Slot", i);
                stack.writeToNBT(entry);
                organList.appendTag(entry);
            }
        }
        root.setTag("Organs", organList);
        root.setInteger("SelectedSlot", selectedSlot);
        return root;
    }

    public void fromNbt(NBTTagCompound root) {
        for (int i = 0; i < SLOT_COUNT; i++) {
            organs[i] = null;
        }

        selectedSlot = root.hasKey("SelectedSlot") ? root.getInteger("SelectedSlot") : -1;

        NBTTagList organList = root.getTagList("Organs", 10);
        for (int i = 0; i < organList.tagCount(); i++) {
            NBTTagCompound entry = organList.getCompoundTagAt(i);
            int slot = entry.getInteger("Slot");
            if (slot >= 0 && slot < SLOT_COUNT) {
                organs[slot] = ItemStack.loadItemStackFromNBT(entry);
            }
        }
    }
}
