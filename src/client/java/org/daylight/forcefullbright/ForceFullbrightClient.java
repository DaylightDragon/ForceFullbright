package org.daylight.forcefullbright;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class ForceFullbrightClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ModKeyBindings.register();
        registerCommands();
	}

	private void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommands.literal("forceFullbright")
                .executes(context -> {
                    BrightnessState.toggleState();
                    Minecraft.getInstance().levelRenderer.allChanged();

                    Minecraft mc = Minecraft.getInstance();
                    if(mc != null && mc.player != null) mc.player.sendSystemMessage(
                            Component.literal(BrightnessState.isEnabled() ?
                                    "§a§lEnabled§f Forced Fullbright" :
                                    "§6§lDisabled§r Forced Fullbright")
                    );
                    return 1;
                })
            );
        });

	}
}
