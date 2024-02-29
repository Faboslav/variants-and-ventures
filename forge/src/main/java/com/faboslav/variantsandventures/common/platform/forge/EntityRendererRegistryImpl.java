package com.faboslav.variantsandventures.common.platform.forge;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public final class EntityRendererRegistryImpl
{
	public static <T extends Entity> void register(
		Supplier<EntityType<T>> type,
		EntityRendererFactory<T> renderProvider
	) {
		EntityRenderers.register(type.get(), renderProvider);
	}

	private EntityRendererRegistryImpl() {
	}
}
