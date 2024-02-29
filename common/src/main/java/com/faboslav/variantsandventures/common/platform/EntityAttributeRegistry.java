package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.function.Supplier;

public final class EntityAttributeRegistry
{
	@ExpectPlatform
	public static void register(
		Supplier<? extends EntityType<? extends LivingEntity>> type,
		Supplier<DefaultAttributeContainer.Builder> attribute
	) {
		throw new AssertionError();
	}

	private EntityAttributeRegistry() {
	}
}
