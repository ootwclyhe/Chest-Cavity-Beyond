package net.zhaiji.chestcavitybeyond.legacy.client.key;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class LegacyKeyBindings {
    public static final KeyBinding USE_ORGAN_SKILL = new KeyBinding("key.chestcavitybeyond.use_organ_skill", Keyboard.KEY_R, "key.categories.gameplay");
    public static final KeyBinding NEXT_ORGAN_SKILL = new KeyBinding("key.chestcavitybeyond.next_organ_skill", Keyboard.KEY_X, "key.categories.gameplay");
    public static final KeyBinding PREV_ORGAN_SKILL = new KeyBinding("key.chestcavitybeyond.prev_organ_skill", Keyboard.KEY_Z, "key.categories.gameplay");

    private LegacyKeyBindings() {
    }

    public static void register() {
        ClientRegistry.registerKeyBinding(USE_ORGAN_SKILL);
        ClientRegistry.registerKeyBinding(NEXT_ORGAN_SKILL);
        ClientRegistry.registerKeyBinding(PREV_ORGAN_SKILL);
    }
}
