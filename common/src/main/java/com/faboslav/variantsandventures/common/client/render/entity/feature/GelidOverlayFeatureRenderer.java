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
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GelidOverlayFeatureRenderer extends FeatureRenderer<ZombieEntityRenderState, DrownedEntityModel> {
	private static final Identifier SKIN = VariantsAndVentures.makeID("textures/entity/gelid/gelid_overlay.png");
	private final DrownedEntityModel model;
	private final DrownedEntityModel babyModel;

	public GelidOverlayFeatureRenderer(FeatureRendererContext<ZombieEntityRenderState, DrownedEntityModel> context, EntityModelLoader loader) {
		super(context);
		this.model = new DrownedEntityModel(loader.getModelPart(EntityModelLayers.DROWNED_OUTER));
		this.babyModel = new DrownedEntityModel(loader.getModelPart(EntityModelLayers.DROWNED_BABY_OUTER));
	}

	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, ZombieEntityRenderState zombieEntityRenderState, float f, float g) {
		DrownedEntityModel drownedEntityModel = zombieEntityRenderState.baby ? this.babyModel : this.model;
		render(drownedEntityModel, SKIN, matrixStack, vertexConsumerProvider, i, zombieEntityRenderState, -1);
	}
}
