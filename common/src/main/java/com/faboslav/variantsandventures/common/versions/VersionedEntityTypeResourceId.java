package com.faboslav.variantsandventures.common.versions;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public final class VersionedEntityTypeResourceId
{
	//? if >=1.21.3 {
	public static ResourceKey<EntityType<?>> create(String id) {
		return ResourceKey.create(Registries.ENTITY_TYPE, VariantsAndVentures.makeID(id));
	}
	//?} else {
	/*public static String create(String id) {
		return id;
	}
	*///?}
}
