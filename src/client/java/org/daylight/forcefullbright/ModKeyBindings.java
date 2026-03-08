package org.daylight.forcefullbright;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;

public class ModKeyBindings {
    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(Identifier.of(ForceFullbright.MOD_ID, "main_category"));

    public static KeyBinding TOGGLE_FULLBRIGHT;

    public static boolean prevToggleFullbrightDown = false;

    public static void register() {
        TOGGLE_FULLBRIGHT = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + ForceFullbright.MOD_ID + ".toggle_fullbright",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        CATEGORY
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.getWindow() == null) return;
            Window window = client.getWindow();

            InputUtil.Key whitelistScreenKey = KeyBindingHelper.getBoundKeyOf(TOGGLE_FULLBRIGHT);
            if (whitelistScreenKey.getCategory() == InputUtil.Type.KEYSYM) {
                if(whitelistScreenKey.getCode() != -1) {
                    boolean down = InputUtil.isKeyPressed(window, whitelistScreenKey.getCode());

                    if (down && !prevToggleFullbrightDown) {
                        BrightnessState.toggleState();
                    }
                    prevToggleFullbrightDown = down;
                }
            }
        });
    }
}
