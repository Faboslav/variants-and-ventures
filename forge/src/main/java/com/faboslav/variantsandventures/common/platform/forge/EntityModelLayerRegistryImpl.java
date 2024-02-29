package com.faboslav.variantsandventures.common.platform.forge;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class EntityModelLayerRegistryImpl
{
	public static final Map<EntityModelLayer, Supplier<TexturedModelData>> ENTITY_MODEL_LAYERS = new ConcurrentHashMap<>();

	public static void register(EntityModelLayer location, Supplier<TexturedModelData> definition) {
		ENTITY_MODEL_LAYERS.put(location, definition);
	}

	private EntityModelLayerRegistryImpl() {
	}
}
