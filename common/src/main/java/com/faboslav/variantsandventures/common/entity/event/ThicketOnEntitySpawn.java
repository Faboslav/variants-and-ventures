package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;

public final class ThicketOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.ZOMBIE,
			VariantsAndVenturesEntityTypes.THICKET.get(),
			VariantsAndVentures.getConfig().getModMobsConfig().enableThicket || VariantsAndVentures.getConfig().getModMobsConfig().enableThicketSpawns,
			VariantsAndVentures.getConfig().getModMobsConfig().thicketSpawnChance,
			VariantsAndVentures.getConfig().getModMobsConfig().thicketMinimumYLevel,
			VariantsAndVenturesTags.HAS_THICKET
		);
	}
}
