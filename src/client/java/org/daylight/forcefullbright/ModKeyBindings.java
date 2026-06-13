package org.daylight.forcefullbright;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

public class ModKeyBindings {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(ForceFullbright.MOD_ID, "main_category"));

    public static KeyMapping TOGGLE_FULLBRIGHT;

    public static boolean prevToggleFullbrightDown = false;

    public static void register() {
        TOGGLE_FULLBRIGHT = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key." + ForceFullbright.MOD_ID + ".toggle_fullbright",
                        InputConstants.Type.KEYSYM,
                        InputConstants.UNKNOWN.getValue(),
                        CATEGORY
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.getWindow() == null) return;
            Window window = client.getWindow();

            InputConstants.Key whitelistScreenKey = KeyMappingHelper.getBoundKeyOf(TOGGLE_FULLBRIGHT);
            if (whitelistScreenKey.getType() == InputConstants.Type.KEYSYM) {
                if(whitelistScreenKey.getValue() != -1) {
                    boolean down = InputConstants.isKeyDown(window, whitelistScreenKey.getValue());

                    if (down && !prevToggleFullbrightDown) {
                        BrightnessState.toggleState();
                        Minecraft.getInstance().levelRenderer.allChanged();
                    }
                    prevToggleFullbrightDown = down;
                }
            }
        });
    }
}
