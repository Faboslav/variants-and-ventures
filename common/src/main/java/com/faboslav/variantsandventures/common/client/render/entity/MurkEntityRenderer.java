package com.faboslav.variantsandventures.common.client.render.entity;

/*? >=1.21.3 {*/
import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.client.render.entity.state.MurkEntityRenderState;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;
import java.util.Map;

public class MurkEntityRenderer extends AbstractSkeletonRenderer<MurkEntity, MurkEntityRenderState>
{
	public static final Map<MurkEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (textures) -> {
		for (MurkEntity.Variant variant : MurkEntity.Variant.VARIANTS) {
			textures.put(variant, VariantsAndVentures.makeID(String.format(Locale.ROOT, "textures/entity/murk/murk_%s.png", variant.getName())));
		}
	});

	public MurkEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModelLayers.BOGGED_INNER_ARMOR, ModelLayers.BOGGED_OUTER_ARMOR, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK)));
	}

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
	public ResourceLocation getTextureLocation(MurkEntityRenderState renderState) {
		return TEXTURES.get(renderState.variant);
	}
}
/*?} else {*/
/*import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;
import java.util.Map;

public class MurkEntityRenderer extends SkeletonRenderer<MurkEntity>
{
	public static final Map<MurkEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (textures) -> {
		for (MurkEntity.Variant variant : MurkEntity.Variant.VARIANTS) {
			textures.put(variant, VariantsAndVentures.makeID(String.format(Locale.ROOT, "textures/entity/murk/murk_%s.png", variant.getName())));
		}
	});

	public MurkEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModelLayers.BOGGED_INNER_ARMOR, ModelLayers.BOGGED_OUTER_ARMOR, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK)));
	}

	@Override
	public ResourceLocation getTextureLocation(MurkEntity murk) {
		return TEXTURES.get(murk.getVariant());
	}
}
*//*?}*/