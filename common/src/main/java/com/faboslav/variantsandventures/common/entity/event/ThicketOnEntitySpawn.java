package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.faboslav.variantsandventures.common.versions.VersionedEntitySpawnReason;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

/*? if >=1.21.3 {*/
import net.minecraft.world.entity.ConversionParams;
/*?}*/

public final class ThicketOnEntitySpawn
{
	public static boolean handleEntitySpawn(EntitySpawnEvent event) {
		Mob entity = event.entity();

		if (event.spawnReason() == VersionedEntitySpawnReason.NATURAL
			|| event.spawnReason() == VersionedEntitySpawnReason.SPAWNER
			|| event.spawnReason() == VersionedEntitySpawnReason.CHUNK_GENERATION
			|| event.spawnReason() == VersionedEntitySpawnReason.STRUCTURE
		) {
			if (entity.getType() != EntityType.ZOMBIE) {
				return false;
			}

			if (VariantsAndVentures.getConfig().enableThicket == false || VariantsAndVentures.getConfig().enableThicketSpawns == false) {
				return false;
			}

			if (event.entity().blockPosition().getY() < VariantsAndVentures.getConfig().thicketMinimumYLevel) {
				return false;
			}

			if (event.entity().getRandom().nextFloat() >= VariantsAndVentures.getConfig().thicketSpawnChance) {
				return false;
			}

			LevelAccessor worldAccess = event.worldAccess();
			Holder<Biome> biome = worldAccess.getBiome(entity.blockPosition());

			if (biome.is(VariantsAndVenturesTags.HAS_THICKET) == false) {
				return false;
			}

			/*? >=1.21.3 {*/
			entity.convertTo(VariantsAndVenturesEntityTypes.THICKET.get(), ConversionParams.single(entity, true, true), (convertedEntity) -> {
				if (!entity.isSilent()) {
					entity.level().levelEvent(null, 1048, entity.blockPosition(), 0);
				}
			});
			/*?} else {*/
			/*entity.convertTo(VariantsAndVenturesEntityTypes.THICKET.get(), true);
			 *//*?}*/

			return true;
		}

		return false;
	}
}
