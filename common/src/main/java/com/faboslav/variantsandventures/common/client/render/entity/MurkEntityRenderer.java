package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.client.render.entity.state.MurkEntityRenderState;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Locale;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class MurkEntityRenderer extends AbstractSkeletonEntityRenderer<MurkEntity, MurkEntityRenderState>
{
	public static final Map<MurkEntity.Variant, Identifier> TEXTURES = Util.make(Maps.newHashMap(), (textures) -> {
		for (MurkEntity.Variant variant : MurkEntity.Variant.VARIANTS) {
			textures.put(variant, VariantsAndVentures.makeID(String.format(Locale.ROOT, "textures/entity/murk/murk_%s.png", variant.getName())));
		}
	});

	public MurkEntityRenderer(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.BOGGED_INNER_ARMOR, EntityModelLayers.BOGGED_OUTER_ARMOR, new MurkEntityModel(context.getPart(VariantsAndVenturesModelLayers.MURK)));
	}

	@Override
	public MurkEntityRenderState createRenderState() {
		return new MurkEntityRenderState();
	}

	@Override
	public void updateRenderState(MurkEntity murk, MurkEntityRenderState murkRenderState, float f) {
		super.updateRenderState(murk, murkRenderState, f);
		murkRenderState.variant = murk.getVariant();
		murkRenderState.sheared = murk.isSheared();
	}

	@Override
	public Identifier getTexture(MurkEntityRenderState renderState) {
		return TEXTURES.get(renderState.variant);
	}
}
