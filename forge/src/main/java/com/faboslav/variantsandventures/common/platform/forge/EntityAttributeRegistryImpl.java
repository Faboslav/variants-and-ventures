package com.faboslav.variantsandventures.common.platform.forge;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class EntityAttributeRegistryImpl
{
	public static final Map<Supplier<? extends EntityType<? extends LivingEntity>>, Supplier<DefaultAttributeContainer.Builder>> ENTITY_ATTRIBUTES = new ConcurrentHashMap<>();

	public static void register(
		Supplier<? extends EntityType<? extends LivingEntity>> type,
		Supplier<DefaultAttributeContainer.Builder> attribute
	) {
		ENTITY_ATTRIBUTES.put(type, attribute);
	}

	private EntityAttributeRegistryImpl() {
	}
}
