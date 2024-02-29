package com.faboslav.variantsandventures.common.platform.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import java.util.function.Supplier;

public final class EntityModelLayerRegistryImpl
{
	public static void register(EntityModelLayer location, Supplier<TexturedModelData> definition) {
		EntityModelLayerRegistry.registerModelLayer(location, definition::get);
	}

	private EntityModelLayerRegistryImpl() {
	}
}
