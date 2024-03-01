package com.faboslav.variantsandventures.common.platform.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Supplier;

public final class EntityTypeRegistryImpl
{
	public static <T extends Entity> Supplier<EntityType<T>> register(
		String name,
		Supplier<EntityType<T>> entityType
	) {
		var registry = Registry.register(Registries.ENTITY_TYPE, VariantsAndVentures.makeID(name), entityType.get());
		return () -> registry;
	}

	private EntityTypeRegistryImpl() {
	}
}
