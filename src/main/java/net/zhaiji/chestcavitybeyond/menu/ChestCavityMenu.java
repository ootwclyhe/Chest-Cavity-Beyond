package net.zhaiji.chestcavitybeyond.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.register.InitMenuType;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ChestCavityMenu extends AbstractContainerMenu {
    private final ChestCavityData data;

    // 客户端使用的构造函数
    public ChestCavityMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, (LivingEntity) playerInventory.player.level().getEntity(extraData.readInt()));
    }

    public ChestCavityMenu(int containerId, Inventory playerInventory, LivingEntity entity) {
        super(InitMenuType.CHEST_CAVITY.get(), containerId);
        data = ChestCavityUtil.getData(entity);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new SlotItemHandler(data, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        // 触发胸腔打开回调
        ChestCavityUtil.chestCavityOpen(data, entity);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        Level level = player.level();
        if (level.isClientSide()) {
            if (ChestCavityUtil.getData(data.getOwner()).hasOrgan(ItemTags.DOORS)) {
                player.playNotifySound(SoundEvents.CHEST_CLOSE, player.getSoundSource(), 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        // 触发胸腔关闭回调
        ChestCavityUtil.chestCavityClose(data, data.getOwner());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 27) {
                if (!this.moveItemStackTo(itemstack1, 27, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 27, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                itemstack1 = ItemStack.EMPTY;
                slot.setByPlayer(itemstack1);
            } else {
                slot.setChanged();
            }
            // 因为moveItemStackTo使用shrink减少物品数量，所以当选择的是胸腔槽位时，需要额外更新器官
            if (index < 27) {
                ChestCavityUtil.changeOrgan(data, data.getOwner(), index, itemstack, itemstack1);
            }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.level().isClientSide()
                || data.getOwner() instanceof LivingEntity entity
                && entity.isAlive()
                // 最大距离为实体交互距离的2倍
                && player.canInteractWithEntity(entity, player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).getValue() * 2);
    }
}
