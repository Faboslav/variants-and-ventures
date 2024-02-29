package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.ThicketOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThicketEntityRenderer extends ZombieBaseEntityRenderer<ThicketEntity, DrownedEntityModel<ThicketEntity>>
{
	public static final Identifier TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket.png");

	public ThicketEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new DrownedEntityModel<>(context.getPart(EntityModelLayers.DROWNED)), new DrownedEntityModel<>(context.getPart(EntityModelLayers.DROWNED_INNER_ARMOR)), new DrownedEntityModel<>(context.getPart(EntityModelLayers.DROWNED_OUTER_ARMOR)));
		this.addFeature(new ThicketOverlayFeatureRenderer<>(this, context.getModelLoader()));
	}

	@Override
	public Identifier getTexture(ThicketEntity thicket) {
		return TEXTURE;
	}
}
