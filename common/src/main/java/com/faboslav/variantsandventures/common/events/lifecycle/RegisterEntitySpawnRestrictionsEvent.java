package com.faboslav.variantsandventures.common.events.lifecycle;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.entity.SpawnPlacements;

//? if >= 1.21 {
import net.minecraft.world.entity.SpawnPlacementType;
//?}

public record RegisterEntitySpawnRestrictionsEvent(Registrar registrar)
{
	public static final EventHandler<RegisterEntitySpawnRestrictionsEvent> EVENT = new EventHandler<>();

	public <T extends Mob> void register(
		EntityType<T> entityType,
		//? if >= 1.21 {
		SpawnPlacementType location,
		//?} else {
		/*SpawnPlacements.Type location,
		*///?}
		Heightmap.Types heightmap,
		SpawnPlacements.SpawnPredicate<T> predicate
	) {
		registrar.register(entityType, new Placement<>(predicate, location, heightmap));
	}

	public record Placement<T extends Mob>(
		SpawnPlacements.SpawnPredicate<T> predicate,
		//? if >= 1.21 {
		SpawnPlacementType location,
		 //?} else {
		/*SpawnPlacements.Type location,
		*///?}
		Heightmap.Types heightmap
	)
	{
	}

	@FunctionalInterface
	public interface Registrar
	{
		<T extends Mob> void register(EntityType<T> type, Placement<T> placement);
	}
}
