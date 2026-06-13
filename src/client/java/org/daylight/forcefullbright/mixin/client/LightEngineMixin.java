package org.daylight.forcefullbright.mixin.client;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockAndLightGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.daylight.forcefullbright.BrightnessState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LevelRenderer.class, priority = 3000)
public class LightEngineMixin {
    @Inject(
            method = "getLightCoords(Lnet/minecraft/client/renderer/LevelRenderer$BrightnessGetter;Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void forcefullbright$getLightCoords1(final LevelRenderer.BrightnessGetter brightnessGetter, final BlockAndLightGetter level, final BlockState state, final BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (BrightnessState.isEnabled()) cir.setReturnValue(15728880);
    }

    @Inject(
            method = "getLightCoords(Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static <E extends Entity> void forcefullbright$getLightCoords2(BlockAndLightGetter level, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(BrightnessState.isEnabled()) cir.setReturnValue(15728880);
    }
}
