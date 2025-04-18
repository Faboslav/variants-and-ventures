package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;

public final class VerdantOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.SKELETON,
			VariantsAndVenturesEntityTypes.VERDANT.get(),
			VariantsAndVentures.getConfig().getModMobsConfig().enableVerdant || VariantsAndVentures.getConfig().getModMobsConfig().enableVerdantSpawns,
			VariantsAndVentures.getConfig().getModMobsConfig().verdantSpawnChance,
			VariantsAndVentures.getConfig().getModMobsConfig().verdantMinimumYLevel,
			VariantsAndVenturesTags.HAS_VERDANT
		);
	}
}
