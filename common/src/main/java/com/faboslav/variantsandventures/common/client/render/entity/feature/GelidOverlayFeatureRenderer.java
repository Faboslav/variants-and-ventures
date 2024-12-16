package com.faboslav.variantsandventures.common.client.render.entity.feature;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class GelidOverlayFeatureRenderer<T extends GelidEntity> extends RenderLayer<T, DrownedModel<T>>
{
	private static final ResourceLocation SKIN = VariantsAndVentures.makeID("textures/entity/gelid/gelid_overlay.png");
	private final DrownedModel<T> model;

	public GelidOverlayFeatureRenderer(
		RenderLayerParent<T, DrownedModel<T>> context,
		EntityModelSet loader
	) {
		super(context);
		this.model = new DrownedModel<>(loader.bakeLayer(ModelLayers.DROWNED_OUTER_LAYER));
	}

	public void render(
		PoseStack matrices,
		MultiBufferSource vertexConsumers,
		int light,
		T thicket,
		float limbAngle,
		float limbDistance,
		float tickDelta,
		float animationProgress,
		float headYaw,
		float headPitch
	) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrices, vertexConsumers, light, thicket, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, -1);
	}
}
