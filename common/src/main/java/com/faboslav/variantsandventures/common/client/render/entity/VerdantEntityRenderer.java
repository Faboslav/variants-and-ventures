package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.faboslav.variantsandventures.common.client.model.VerdantEntityModel;


//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
//?} else {
//?}

//? if >= 1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
//?} else {
/*import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
*///?}

//? if >= 1.21.3 {
public class VerdantEntityRenderer extends AbstractSkeletonRenderer<VerdantEntity, SkeletonRenderState>
//? } else {
/*public class VerdantEntityRenderer extends HumanoidMobRenderer<VerdantEntity, VerdantEntityModel>
*///?}
{
	private static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/verdant/verdant.png");

	public VerdantEntityRenderer(EntityRendererProvider.Context context) {
		//? if >= 1.21.9 {
		super(context, VariantsAndVenturesModelLayers.VERDANT_ARMOR, new VerdantEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.VERDANT)));
		//?} else if >= 1.21.3 {
		/*super(context, VariantsAndVenturesModelLayers.VERDANT_INNER_ARMOR, VariantsAndVenturesModelLayers.VERDANT_OUTER_ARMOR, new VerdantEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.VERDANT)));
		 *///?} else {
		/*super(context, new VerdantEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.VERDANT)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new VerdantEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.VERDANT_INNER_ARMOR)), new VerdantEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.VERDANT_OUTER_ARMOR)), context.getModelManager()));
		*///?}
	}

	//? if >= 1.21.3 {
	@Override
	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(SkeletonRenderState renderState) {
		return TEXTURE;
	}
	//?} else {
	/*@Override
	public ResourceLocation getTextureLocation(VerdantEntity verdant) {
		return TEXTURE;
	}
	*///?}
}