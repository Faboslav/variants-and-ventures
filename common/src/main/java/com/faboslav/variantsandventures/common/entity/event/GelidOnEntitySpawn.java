package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.world.entity.EntityType;
public final class GelidOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		return OnEntitySpawn.handleOnEntitySpawn(
			event,
			EntityType.ZOMBIE,
			VariantsAndVenturesEntityTypes.GELID.get(),
			VariantsAndVentures.getConfig().modMobs.enableGelid || VariantsAndVentures.getConfig().modMobs.enableGelidSpawns,
			VariantsAndVentures.getConfig().modMobs.gelidSpawnChance,
			VariantsAndVentures.getConfig().modMobs.gelidMinimumYLevel,
			VariantsAndVenturesTags.HAS_GELID
		);
	}
}
