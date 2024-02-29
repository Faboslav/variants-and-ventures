package com.faboslav.variantsandventures.common.platform.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class EntityTypeRegistryImpl
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VariantsAndVentures.MOD_ID);

	public static <T extends Entity> Supplier<EntityType<T>> register(
		String name,
		Supplier<EntityType<T>> entityType
	) {
		return ENTITY_TYPES.register(name, entityType);
	}

	private EntityTypeRegistryImpl() {
	}
}
