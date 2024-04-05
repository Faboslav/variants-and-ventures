package com.faboslav.variantsandventures.common.events.lifecycle;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;

public record RegisterEntitySpawnRestrictionsEvent(Registrar registrar)
{
	public static final EventHandler<RegisterEntitySpawnRestrictionsEvent> EVENT = new EventHandler<>();

	public <T extends MobEntity> void register(
		EntityType<T> entityType,
		SpawnRestriction.Location location,
		Heightmap.Type heightmap,
		SpawnRestriction.SpawnPredicate<T> predicate
	) {
		registrar.register(entityType, new Placement<>(predicate, location, heightmap));
	}

	public record Placement<T extends MobEntity>(SpawnRestriction.SpawnPredicate<T> predicate,
												 SpawnRestriction.Location location, Heightmap.Type heightmap)
	{
	}

	@FunctionalInterface
	public interface Registrar
	{
		<T extends MobEntity> void register(EntityType<T> type, Placement<T> placement);
	}
}
