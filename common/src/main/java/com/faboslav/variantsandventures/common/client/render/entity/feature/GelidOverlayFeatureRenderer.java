package com.faboslav.variantsandventures.common.client.render.entity.feature;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GelidOverlayFeatureRenderer<T extends GelidEntity> extends FeatureRenderer<T, DrownedEntityModel<T>>
{
	private static final Identifier SKIN = VariantsAndVentures.makeID("textures/entity/gelid/gelid_overlay.png");
	private final DrownedEntityModel<T> model;

	public GelidOverlayFeatureRenderer(
		FeatureRendererContext<T, DrownedEntityModel<T>> context,
		EntityModelLoader loader
	) {
		super(context);
		this.model = new DrownedEntityModel<>(loader.getModelPart(EntityModelLayers.DROWNED_OUTER));
	}

	public void render(
		MatrixStack matrixStack,
		VertexConsumerProvider vertexConsumerProvider,
		int i,
		T drownedEntity,
		float f,
		float g,
		float h,
		float j,
		float k,
		float l
	) {
		render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, drownedEntity, f, g, j, k, l, h, 1.0F, 1.0F, 1.0F);
	}
}
