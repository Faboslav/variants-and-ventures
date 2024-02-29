package com.faboslav.variantsandventures.common.platform.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public final class EntityRendererRegistryImpl
{
	public static <T extends Entity> void register(
		Supplier<EntityType<T>> type,
		EntityRendererFactory<T> renderProvider
	) {
		EntityRendererRegistry.register(type.get(), renderProvider);
	}

	private EntityRendererRegistryImpl() {
	}
}
