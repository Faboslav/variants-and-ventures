package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.platform.EntityModelLayerRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkullEntityModel;

/**
 * @see EntityModelLayers
 */
@Environment(EnvType.CLIENT)
public final class VariantsAndVenturesModelLayers
{
	public static final EntityModelLayer GELID_HEAD = new EntityModelLayer(VariantsAndVentures.makeID("gelid"), "outer");

	public static void init() {
		EntityModelLayerRegistry.register(GELID_HEAD, SkullEntityModel::getHeadTexturedModelData);
	}

	private VariantsAndVenturesModelLayers() {
	}
}
