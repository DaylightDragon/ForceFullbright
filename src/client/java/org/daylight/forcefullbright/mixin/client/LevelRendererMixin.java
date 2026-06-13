package org.daylight.forcefullbright.mixin.client;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndLightGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.daylight.forcefullbright.BrightnessState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Inject(
            method = "getLightCoords(Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void forcefullbright$modifyLight(BlockAndLightGetter level, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(BrightnessState.isEnabled()) {
            int custom = 15728880;
            cir.setReturnValue(custom);
        }
    }

    @Inject(
            method = "getLightCoords(Lnet/minecraft/client/renderer/LevelRenderer$BrightnessGetter;Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
                    )
    private static void forcefullbright$modifyLight2(LevelRenderer.BrightnessGetter brightnessGetter, BlockAndLightGetter level, BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(BrightnessState.isEnabled()) {
            int custom = 15728880;
            cir.setReturnValue(custom);
        }
    }
}
