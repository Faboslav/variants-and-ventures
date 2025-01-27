package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;

public final class HuskOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.ZOMBIE,
			EntityType.HUSK,
			VariantsAndVentures.getConfig().vanillaMobs.enableBetterHuskSpawns,
			VariantsAndVentures.getConfig().vanillaMobs.huskSpawnChance,
			VariantsAndVentures.getConfig().vanillaMobs.huskMinimumYLevel,
			VariantsAndVenturesTags.HAS_HUSK
		);
	}
}
