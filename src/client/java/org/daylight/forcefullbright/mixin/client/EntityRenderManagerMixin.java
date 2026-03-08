package org.daylight.forcefullbright.mixin.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.daylight.forcefullbright.BrightnessState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityRenderManager.class, priority = 3000)
public class EntityRenderManagerMixin {
    @ModifyVariable(
            method = "Lnet/minecraft/client/render/entity/EntityRenderManager;render(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/render/state/CameraRenderState;DDDLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private EntityRenderState modifyRenderState(EntityRenderState state) {
        if (BrightnessState.isEnabled()) state.light = 15728880;
        return state;
    }

    @Inject(
            method = "getLight(Lnet/minecraft/entity/Entity;F)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private <E extends Entity> void fullBright(E entity, float tickProgress, CallbackInfoReturnable<Integer> cir) {
        if(BrightnessState.isEnabled()) cir.setReturnValue(15728880);
    }
}
