package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.GelidOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class GelidEntityRenderer extends AbstractZombieRenderer<GelidEntity, DrownedModel<GelidEntity>>
{
	public static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/gelid/gelid.png");

	public GelidEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED)), new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED_INNER_ARMOR)), new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED_OUTER_ARMOR)));
		this.addLayer(new GelidOverlayFeatureRenderer<>(this, context.getModelSet()));
	}

	@Override
	protected void scale(GelidEntity gelid, PoseStack matrixStack, float f) {
		matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(gelid, matrixStack, f);
	}

	@Override
	public ResourceLocation getTextureLocation(GelidEntity gelid) {
		return TEXTURE;
	}

	@Override
	protected void setupRotations(
		GelidEntity gelid,
		PoseStack matrices,
		float animationProgress,
		float bodyYaw,
		float tickDelta,
		float scale
	) {
		super.setupRotations(gelid, matrices, animationProgress, bodyYaw, tickDelta, scale);

		float i = gelid.getSwimAmount(tickDelta);
		if (i > 0.0F) {
			matrices.mulPose(Axis.XP.rotationDegrees(Mth.lerp(i, gelid.getXRot(), -10.0F - gelid.getXRot())));
		}
	}
}
