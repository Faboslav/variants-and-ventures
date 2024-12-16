package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.ThicketOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ThicketEntityRenderer extends AbstractZombieRenderer<ThicketEntity, DrownedModel<ThicketEntity>>
{
	public static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket.png");

	public ThicketEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED)), new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED_INNER_ARMOR)), new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED_OUTER_ARMOR)));
		this.addLayer(new ThicketOverlayFeatureRenderer<>(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(ThicketEntity thicket) {
		return TEXTURE;
	}
}
