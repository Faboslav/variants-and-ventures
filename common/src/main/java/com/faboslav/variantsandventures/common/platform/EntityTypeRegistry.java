package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public final class EntityTypeRegistry
{
	@ExpectPlatform
	public static <T extends Entity> Supplier<EntityType<T>> register(
		String name,
		Supplier<EntityType<T>> entityType
	) {
		throw new AssertionError();
	}

	private EntityTypeRegistry() {
	}
}
