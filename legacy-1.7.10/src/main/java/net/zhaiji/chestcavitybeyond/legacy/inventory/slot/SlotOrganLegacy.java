package net.zhaiji.chestcavitybeyond.legacy.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.api.organ.LegacyOrganRegistry;

public class SlotOrganLegacy extends Slot {
    public SlotOrganLegacy(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack != null && LegacyOrganRegistry.get(stack.getItem()) != null;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}
