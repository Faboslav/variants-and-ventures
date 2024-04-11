package com.faboslav.variantsandventures.common.config;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.config.annotation.Category;
import com.faboslav.variantsandventures.common.config.annotation.Description;
import com.faboslav.variantsandventures.common.config.omegaconfig.api.Config;

public final class VariantsAndVenturesConfig implements Config
{
	@Category("General")
	@Description("Check if the new update of mod is available (when enabled, info is in the console)")
	public boolean checkForNewUpdates = true;

	@Category("Zombies")
	@Description("Enable experimental keyframe animations for zombie and its variants")
	public boolean enableKeyframeAnimationsForZombieAndItsVariants = true;

	@Category("Skeletons")
	@Description("Enable experimental keyframe animations for skeleton and its variants")
	public boolean enableKeyframeAnimationsForSkeletonAndItsVariants = true;

	@Category("Stray")
	@Description("Enable better Stray spawns")
	public boolean enableBetterStraySpawns = true;

	@Description("Spawn chance of Stray (For example 0.8 means that stray will spawn in place of 80% of all skeletons spawned in cold biomes.")
	public float straySpawnChance = 0.8F;

	@Description("Minimum Y level for Stray spawns (For example 62 means that stray will spawn only above the ocean Y level.")
	public float strayMinimumYLevel = -64;

	@Description("Enable spawners in dungeons")
	public boolean enableStraySpawners = true;

	@Description("Chance of Stray spawner in dungeons (For example 0.8 means that stray spawner will be in place of 80% of all skeleton spawners in cold biomes.")
	public float straySpawnerChance = 0.8F;

	@Category("Husk")
	@Description("Enable better Husk spawns")
	public boolean enableBetterHuskSpawns = true;

	@Description("Spawn chance of Husk (For example 0.8 means that husk will spawn in place of 80% of all skeletons spawned in cold biomes.")
	public float huskSpawnChance = 0.8F;

	@Description("Minimum Y level for Husk spawns (For example 62 means that husk will spawn only above the ocean Y level.")
	public float huskMinimumYLevel = -64;

	@Description("Enable spawners in dungeons")
	public boolean enableHuskSpawners = true;

	@Description("Chance of Husk spawner in dungeons (For example 0.8 means that husk spawner will be in place of 80% of all skeleton spawners in cold biomes.")
	public float huskSpawnerChance = 0.8F;

	@Category("Gelid (Frozen Zombie)")
	@Description("Enable Gelid")
	public boolean enableGelid = true;

	@Description("Enable Gelid spawns")
	public boolean enableGelidSpawns = true;

	@Description("Spawn chance of Gelid (For example 0.8 means that gelid will spawn in place of 80% of all zombies spawned in cold biomes.")
	public float gelidSpawnChance = 0.8F;

	@Description("Minimum Y level for Gelid spawns (For example 62 means that gelid will spawn only above the ocean Y level.")
	public float gelidMinimumYLevel = -64;

	@Description("Enable Gelid spawners in dungeons")
	public boolean enableGelidSpawners = true;

	@Description("Chance of Gelid spawner in dungeons (For example 0.8 means that gelid spawner will be in place of 80% of all zombie spawners in cold biomes.")
	public float gelidSpawnerChance = 0.8F;

	@Category("Murk (Sunken Skeleton)")
	@Description("Enable Murk")
	public boolean enableMurk = true;

	@Description("Enable Murk spawns")
	public boolean enableMurkSpawns = true;

	@Category("Thicket (Jungle Zombie)")
	@Description("Enable Thickets")
	public boolean enableThicket = true;

	@Description("Enable Thickets spawns")
	public boolean enableThicketSpawns = true;

	@Description("Spawn chance of Thicket (For example 0.8 means that thicket will spawn in place of 80% of all zombies spawned in cold biomes.")
	public float thicketSpawnChance = 0.8F;

	@Description("Minimum Y level for Thicket spawns (For example 62 means that thicket will spawn only above the ocean Y level.")
	public float thicketMinimumYLevel = -64;

	@Description("Enable Thicket spawners in dungeons")
	public boolean enableThicketSpawners = true;

	@Description("Chance of Thicket spawner in dungeons (For example 0.8 means that thicket spawner will be in place of 80% of all zombie spawners in cold biomes.")
	public float thicketSpawnerChance = 0.8F;

	@Category("Verdant (Jungle Skeleton)")
	@Description("Enable Verdant")
	public boolean enableVerdant = true;

	@Description("Enable Verdant spawns")
	public boolean enableVerdantSpawns = true;

	@Description("Spawn chance of Verdant (For example 0.8 means that verdant will spawn in place of 80% of all skeletons spawned in jungle biomes.")
	public float verdantSpawnChance = 0.8F;

	@Description("Minimum Y level for Verdant spawns (For example 62 means that verdant will spawn only above the ocean Y level.")
	public float verdantMinimumYLevel = -64;

	@Description("Enable Verdant spawners in dungeons")
	public boolean enableVerdantSpawners = true;

	@Description("Chance of Verdant spawner in dungeons (For example 0.8 means that verdant spawner will be in place of 80% of all skeleton spawners in jungle biomes.")
	public float verdantSpawnerChance = 0.8F;

	@Override
	public String getName() {
		return VariantsAndVentures.MOD_ID;
	}
}
