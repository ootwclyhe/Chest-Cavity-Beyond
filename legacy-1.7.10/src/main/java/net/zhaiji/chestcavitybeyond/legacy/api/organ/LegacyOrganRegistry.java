package net.zhaiji.chestcavitybeyond.legacy.api.organ;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class LegacyOrganRegistry {
    private static final Map<Item, ILegacyOrgan> ORGANS = new HashMap<Item, ILegacyOrgan>();

    private LegacyOrganRegistry() {
    }

    public static void register(Item item, ILegacyOrgan organ) {
        ORGANS.put(item, organ);
    }

    public static ILegacyOrgan get(Item item) {
        return ORGANS.get(item);
    }
}
