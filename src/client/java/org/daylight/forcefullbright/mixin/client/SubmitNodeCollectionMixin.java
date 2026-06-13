package org.daylight.forcefullbright.mixin.client;

import net.minecraft.client.renderer.SubmitNodeCollection;
import org.daylight.forcefullbright.BrightnessState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SubmitNodeCollection.class)
public class SubmitNodeCollectionMixin {
    private static final int FULLBRIGHT = 15728880;

    @ModifyArg(
            method = "submitNameTag",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/feature/NameTagFeatureRenderer$Storage;add(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/phys/Vec3;ILnet/minecraft/network/chat/Component;ZIDLnet/minecraft/client/renderer/state/level/CameraRenderState;)V"
            ),
            index = 5
    )
    private int forceLightOnNameTag(int lightCoords) {
        return BrightnessState.isEnabled() ? FULLBRIGHT : lightCoords;
    }

    @ModifyArg(
            method = "submitText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeStorage$TextSubmit;<init>(Lorg/joml/Matrix4fc;FFLnet/minecraft/util/FormattedCharSequence;ZLnet/minecraft/client/gui/Font$DisplayMode;IIII)V"
            ),
            index = 6
    )
    private int forceLightOnText(int lightCoords) {
        return BrightnessState.isEnabled() ? FULLBRIGHT : lightCoords;
    }

    @ModifyArg(
            method = "submitModel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeStorage$ModelSubmit;<init>(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/model/Model;Ljava/lang/Object;IIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
            ),
            index = 3
    )
    private int forceLightOnModel(int lightCoords) {
        return BrightnessState.isEnabled() ? FULLBRIGHT : lightCoords;
    }

    @ModifyArg(
            method = "submitModelPart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeStorage$ModelPartSubmit;<init>(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/model/geom/ModelPart;IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ZZILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;I)V"
            ),
            index = 2
    )
    private int forceLightOnModelPart(int lightCoords) {
        return BrightnessState.isEnabled() ? FULLBRIGHT : lightCoords;
    }

    @ModifyArg(
            method = "submitBlockModel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeStorage$BlockModelSubmit;<init>(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/rendertype/RenderType;Ljava/util/List;[IIII)V"
            ),
            index = 4
    )
    private int forceLightOnBlockModel(int lightCoords) {
        return BrightnessState.isEnabled() ? FULLBRIGHT : lightCoords;
    }

    @ModifyArg(
            method = "submitItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeStorage$ItemSubmit;<init>(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/world/item/ItemDisplayContext;III[ILjava/util/List;Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;)V"
            ),
            index = 3
    )
    private int forceLightOnItem(int lightCoords) {
        return BrightnessState.isEnabled() ? FULLBRIGHT : lightCoords;
    }
}
