package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public final class EntityRendererRegistry
{
	@ExpectPlatform
	public static <T extends Entity> void register(
		Supplier<EntityType<T>> type,
		EntityRendererFactory<T> renderProvider
	) {
		throw new AssertionError();
	}

	private EntityRendererRegistry() {
	}
}
