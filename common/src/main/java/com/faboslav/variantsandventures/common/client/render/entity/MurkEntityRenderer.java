package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Locale;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class MurkEntityRenderer extends BipedEntityRenderer<MurkEntity, MurkEntityModel>
{
	public static final Map<MurkEntity.Variant, Identifier> TEXTURES = Util.make(Maps.newHashMap(), (textures) -> {
		for (MurkEntity.Variant variant : MurkEntity.Variant.VARIANTS) {
			textures.put(variant, VariantsAndVentures.makeID(String.format(Locale.ROOT, "textures/entity/murk/murk_%s.png", variant.getName())));
		}
	});

	public MurkEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new MurkEntityModel(context.getPart(VariantsAndVenturesModelLayers.MURK)), 0.5F);
		this.addFeature(new ArmorFeatureRenderer<>(this, new SkeletonEntityModel(context.getPart(EntityModelLayers.SKELETON_INNER_ARMOR)), new SkeletonEntityModel(context.getPart(EntityModelLayers.SKELETON_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public Identifier getTexture(MurkEntity murk) {
		return TEXTURES.get(murk.getVariant());
	}
}
