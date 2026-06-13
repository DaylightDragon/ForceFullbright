package org.daylight.forcefullbright.mixin.client;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.daylight.forcefullbright.BrightnessState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @ModifyVariable(
            method = "submit",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    public EntityRenderState forcefullbright$render(EntityRenderState state) {
        if(BrightnessState.isEnabled()) state.lightCoords = 15728880;
        return state;
    }
}
