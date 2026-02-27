package net.zhaiji.chestcavitybeyond.legacy.container;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.zhaiji.chestcavitybeyond.legacy.chestcavity.InventoryChestCavity;
import net.zhaiji.chestcavitybeyond.legacy.inventory.slot.SlotOrganLegacy;

public class ContainerChestCavityLegacy extends Container {
    private final InventoryChestCavity chestCavity;

    public ContainerChestCavityLegacy(InventoryPlayer playerInventory, EntityLivingBase target) {
        this.chestCavity = new InventoryChestCavity(target);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlotToContainer(new SlotOrganLegacy(chestCavity, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlotToContainer(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return chestCavity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack original = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            original = stackInSlot.copy();

            if (index < 27) {
                if (!this.mergeItemStack(stackInSlot, 27, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, 27, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }

        return original;
    }
}
