package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;

/**
 * @see ModelLayers
 */
public final class VariantsAndVenturesModelLayers
{
	public static final ModelLayerLocation MURK = new ModelLayerLocation(VariantsAndVentures.makeID("murk"), "main");

	public static void registerEntityLayers(RegisterEntityLayersEvent event) {
		event.register(MURK, MurkEntityModel::createBodyLayer);
	}

	private VariantsAndVenturesModelLayers() {
	}
}
