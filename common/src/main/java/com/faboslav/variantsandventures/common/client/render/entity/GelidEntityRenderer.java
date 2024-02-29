package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.GelidOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class GelidEntityRenderer extends ZombieBaseEntityRenderer<GelidEntity, DrownedEntityModel<GelidEntity>>
{
	public static final Identifier TEXTURE = VariantsAndVentures.makeID("textures/entity/gelid/gelid.png");

	public GelidEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new DrownedEntityModel<>(context.getPart(EntityModelLayers.DROWNED)), new DrownedEntityModel<>(context.getPart(EntityModelLayers.DROWNED_INNER_ARMOR)), new DrownedEntityModel<>(context.getPart(EntityModelLayers.DROWNED_OUTER_ARMOR)));
		this.addFeature(new GelidOverlayFeatureRenderer<>(this, context.getModelLoader()));
	}

	@Override
	protected void scale(GelidEntity gelid, MatrixStack matrixStack, float f) {
		matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(gelid, matrixStack, f);
	}

	@Override
	public Identifier getTexture(GelidEntity gelid) {
		return TEXTURE;
	}

	@Override
	protected void setupTransforms(GelidEntity gelid, MatrixStack matrixStack, float f, float g, float h) {
		super.setupTransforms(gelid, matrixStack, f, g, h);
		float i = gelid.getLeaningPitch(h);
		if (i > 0.0F) {
			matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.lerp(i, gelid.getPitch(), -10.0F - gelid.getPitch())));
		}
	}
}
