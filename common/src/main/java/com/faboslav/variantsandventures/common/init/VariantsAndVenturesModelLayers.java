package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.client.render.entity.model.MurkSkullEntityModel;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;

/**
 * @see EntityModelLayers
 */
@Environment(EnvType.CLIENT)
public final class VariantsAndVenturesModelLayers
{
	public static final EntityModelLayer MURK = new EntityModelLayer(VariantsAndVentures.makeID("murk"), "main");
	public static final EntityModelLayer MURK_SKULL = new EntityModelLayer(VariantsAndVentures.makeID("murk_skull"), "main");

	public static void registerEntityLayers(RegisterEntityLayersEvent event) {
		event.register(MURK, MurkEntityModel::getTexturedModelData);
		event.register(MURK_SKULL, MurkSkullEntityModel::getSkullTexturedModelData);
	}

	private VariantsAndVenturesModelLayers() {
	}
}
