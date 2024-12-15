package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.GelidOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.feature.ThicketOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThicketEntityRenderer extends ZombieBaseEntityRenderer<ThicketEntity, ZombieEntityRenderState, DrownedEntityModel>
{
	public static final Identifier TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket.png");

	public ThicketEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_BABY)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_INNER_ARMOR)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_OUTER_ARMOR)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_BABY_INNER_ARMOR)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_BABY_OUTER_ARMOR)));
		this.addFeature(new ThicketOverlayFeatureRenderer(this, context.getModelLoader()));
	}

	public ZombieEntityRenderState createRenderState() {
		return new ZombieEntityRenderState();
	}

	public Identifier getTexture(ZombieEntityRenderState zombieEntityRenderState) {
		return TEXTURE;
	}
}
