package org.daylight.forcefullbright;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

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
                    return 1;
                })
            );
        });

	}
}
