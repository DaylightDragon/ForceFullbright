package org.daylight.forcefullbright.mixin.client;

import net.minecraft.client.renderer.SubmitNodeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SubmitNodeCollection.class)
public class SubmitNodeCollectionMixin {
    @ModifyVariable(
            method = "submitNameTag",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 5
    )
    private int forceLightOnNameTag(int lightCoords) {
        return 15728880;
    }

    @ModifyVariable(
            method = "submitText",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 7
    )
    private int forceLightOnText(int lightCoords) {
        return 15728880;
    }

    @ModifyVariable(
            method = "submitModel",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 5
    )
    private int forceLightOnModel(int lightCoords) {
        return 15728880;
    }

    @ModifyVariable(
            method = "submitModelPart",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 4
    )
    private int forceLightOnModelPart(int lightCoords) {
        return 15728880;
    }

    @ModifyVariable(
            method = "submitBlockModel",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 5
    )
    private int forceLightOnBlockModel(int lightCoords) {
        return 15728880;
    }

    @ModifyVariable(
            method = "submitItem",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 3
    )
    private int forceLightOnItem(int lightCoords) {
        return 15728880;
    }
}
