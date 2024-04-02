package com.faboslav.variantsandventures.common.events.lifecycle;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.world.biome.Biome;

public record AddSpawnBiomeModificationEvent(Registrar registrar)
{
	public static final EventHandler<AddSpawnBiomeModificationEvent> EVENT = new EventHandler<>();

	public void add(
		TagKey<Biome> tag,
		SpawnGroup spawnGroup,
		EntityType<?> entityType,
		int weight,
		int minGroupSize,
		int maxGroupSize
	) {
		registrar.add(tag, spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
	}

	@FunctionalInterface
	public interface Registrar
	{
		void add(
			TagKey<Biome> tag,
			SpawnGroup spawnGroup,
			EntityType<?> entityType,
			int weight,
			int minGroupSize,
			int maxGroupSize
		);
	}
}
