package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;

//? if >= 1.21.3 {
import com.faboslav.variantsandventures.common.client.render.entity.state.MurkEntityRenderState;
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
public class MurkEntityRenderer extends AbstractSkeletonRenderer<MurkEntity, MurkEntityRenderState>
 //? } else {
/*public class MurkEntityRenderer extends HumanoidMobRenderer<MurkEntity, MurkEntityModel>
*///?}
{
	public static final Map<MurkEntity.Variant, Identifier> TEXTURES = Util.make(Maps.newHashMap(), (textures) -> {
		for (MurkEntity.Variant variant : MurkEntity.Variant.VARIANTS) {
			textures.put(variant, VariantsAndVentures.makeID(String.format(Locale.ROOT, "textures/entity/murk/murk_%s.png", variant.getName())));
		}
	});

	public MurkEntityRenderer(EntityRendererProvider.Context context) {
		//? if >= 1.21.9 {
		super(context, VariantsAndVenturesModelLayers.MURK_ARMOR, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK)));
		//?} else if >= 1.21.3 {
		/*super(context, VariantsAndVenturesModelLayers.MURK_INNER_ARMOR, VariantsAndVenturesModelLayers.MURK_OUTER_ARMOR, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK)));
		*///?} else {
		/*super(context, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK_INNER_ARMOR)), new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK_OUTER_ARMOR)), context.getModelManager()));
		*///?}
	}

	//? if >= 1.21.3 {
	@Override
	public MurkEntityRenderState createRenderState() {
		return new MurkEntityRenderState();
	}

	@Override
	public void extractRenderState(MurkEntity murk, MurkEntityRenderState murkRenderState, float partialTick) {
		super.extractRenderState(murk, murkRenderState, partialTick);
		murkRenderState.variant = murk.getVariant();
		murkRenderState.sheared = murk.isSheared();
	}

	@Override
	public Identifier getTextureLocation(MurkEntityRenderState renderState) {
		return TEXTURES.get(renderState.variant);
	}
	//?} else {
	/*@Override
	public ResourceLocation getTextureLocation(MurkEntity murk) {
		return TEXTURES.get(murk.getVariant());
	}
	*///?}
}