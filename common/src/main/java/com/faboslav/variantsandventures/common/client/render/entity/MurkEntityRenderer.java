package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import java.util.Locale;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class MurkEntityRenderer extends HumanoidMobRenderer<MurkEntity, MurkEntityModel>
{
	public static final Map<MurkEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (textures) -> {
		for (MurkEntity.Variant variant : MurkEntity.Variant.VARIANTS) {
			textures.put(variant, VariantsAndVentures.makeID(String.format(Locale.ROOT, "textures/entity/murk/murk_%s.png", variant.getName())));
		}
	});

	public MurkEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new MurkEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.MURK)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(MurkEntity murk) {
		return TEXTURES.get(murk.getVariant());
	}
}
