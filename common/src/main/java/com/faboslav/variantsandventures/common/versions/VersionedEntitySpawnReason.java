package com.faboslav.variantsandventures.common.versions;

/*? >=1.21.3 {*/
import net.minecraft.world.entity.EntitySpawnReason;
 /*?} else {*/
/*import net.minecraft.world.entity.MobSpawnType;
*//*?}*/

public class VersionedEntitySpawnReason
{
	/*? >=1.21.3 {*/
	public static final EntitySpawnReason NATURAL = EntitySpawnReason.NATURAL;
	public static final EntitySpawnReason SPAWNER = EntitySpawnReason.SPAWNER;
	public static final EntitySpawnReason CHUNK_GENERATION = EntitySpawnReason.CHUNK_GENERATION;
	public static final EntitySpawnReason STRUCTURE = EntitySpawnReason.STRUCTURE;
	/*?} else {*/
	/*public static final MobSpawnType NATURAL = MobSpawnType.NATURAL;
	public static final MobSpawnType SPAWNER = MobSpawnType.SPAWNER;
	public static final MobSpawnType CHUNK_GENERATION = MobSpawnType.CHUNK_GENERATION;
	public static final MobSpawnType STRUCTURE = MobSpawnType.STRUCTURE;
	*//*?}*/
}
