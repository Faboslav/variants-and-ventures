package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class VerdantEntityRenderer extends AbstractSkeletonEntityRenderer<VerdantEntity, SkeletonEntityRenderState>
{
	public static final Identifier TEXTURE = VariantsAndVentures.makeID("textures/entity/verdant/verdant.png");

	public VerdantEntityRenderer(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
	}

	public SkeletonEntityRenderState createRenderState() {
		return new SkeletonEntityRenderState();
	}

	public Identifier getTexture(SkeletonEntityRenderState skeletonEntityRenderState) {
		return TEXTURE;
	}
}
