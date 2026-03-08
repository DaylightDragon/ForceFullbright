package org.daylight.forcefullbright;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ForceFullbrightClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ModKeyBindings.register();
        registerCommands();
	}

	private void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("forceFullbright")
                .executes(context -> {
                    BrightnessState.toggleState();

                    MinecraftClient mc = MinecraftClient.getInstance();
                    if(mc != null && mc.player != null) mc.player.sendMessage(
                            Text.literal(BrightnessState.isEnabled() ?
                                    "§a§lEnabled§f fullbright" :
                                    "§6§lDisabled§r fullbright"), false
                    );
                    return 1;
                })
            );
        });

	}
}
