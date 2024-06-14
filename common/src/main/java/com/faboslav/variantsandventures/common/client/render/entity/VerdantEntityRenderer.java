package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class VerdantEntityRenderer extends BipedEntityRenderer<VerdantEntity, SkeletonEntityModel<VerdantEntity>>
{
	public static final Identifier TEXTURE = VariantsAndVentures.makeID("textures/entity/verdant/verdant.png");

	public VerdantEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new SkeletonEntityModel<>(context.getPart(EntityModelLayers.SKELETON)), 0.5F);
		this.addFeature(new ArmorFeatureRenderer<>(this, new SkeletonEntityModel(context.getPart(EntityModelLayers.SKELETON_INNER_ARMOR)), new SkeletonEntityModel(context.getPart(EntityModelLayers.SKELETON_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public Identifier getTexture(VerdantEntity verdant) {
		return TEXTURE;
	}
}
