package com.faboslav.variantsandventures.common.platform.fabric;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.function.Supplier;

public final class EntityAttributeRegistryImpl
{
	public static void register(
		Supplier<? extends EntityType<? extends LivingEntity>> type,
		Supplier<DefaultAttributeContainer.Builder> attribute
	) {
		FabricDefaultAttributeRegistry.register(type.get(), attribute.get());
	}

	private EntityAttributeRegistryImpl() {
	}
}
