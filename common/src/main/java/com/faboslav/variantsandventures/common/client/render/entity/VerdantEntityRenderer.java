package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class VerdantEntityRenderer extends HumanoidMobRenderer<VerdantEntity, SkeletonModel<VerdantEntity>>
{
	public static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/verdant/verdant.png");

	public VerdantEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(VerdantEntity verdant) {
		return TEXTURE;
	}
}
