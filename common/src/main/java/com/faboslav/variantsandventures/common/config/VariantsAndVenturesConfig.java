package com.faboslav.variantsandventures.common.config;

public final class VariantsAndVenturesConfig
{
	public static final boolean ENABLE_GELID_DEFAULT_VALUE = true;
	public static final boolean ENABLE_GELID_SPAWNS_DEFAULT_VALUE = true;
	public static final double GELID_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int GELID_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_GELID_SPAWNERS_DEFAULT_VALUE = true;
	public static final double GELID_SPAWNER_CHANCE_DEFAULT_VALUE = 80;
	//? if >= 1.21 {
	public static final boolean ENABLE_GELID_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE = true;
	//?}

	public static final boolean ENABLE_MURK_DEFAULT_VALUE = true;
	public static final boolean ENABLE_MURK_SPAWNS_DEFAULT_VALUE = true;
	public static final int MURK_SPAWN_WEIGHT_DEFAULT_VALUE = 4;
	public static final int MURK_SPAWN_MIN_GROUP_SIZE_DEFAULT_VALUE = 1;
	public static final int MURK_SPAWN_MAX_GROUP_SIZE_DEFAULT_VALUE = 1;
	//? if >= 1.21 {
	public static final boolean ENABLE_MURK_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE = true;
	//?}

	public static final boolean ENABLE_THICKET_DEFAULT_VALUE = true;
	public static final boolean ENABLE_THICKET_SPAWNS_DEFAULT_VALUE = true;
	public static final double THICKET_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int THICKET_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_THICKET_SPAWNERS_DEFAULT_VALUE = true;
	public static final double THICKET_SPAWNER_CHANCE_DEFAULT_VALUE = 80;
	public static final boolean ENABLE_THICKET_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE = true;

	public static final boolean ENABLE_VERDANT_DEFAULT_VALUE = true;
	public static final boolean ENABLE_VERDANT_SPAWNS_DEFAULT_VALUE = true;
	public static final double VERDANT_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int VERDANT_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_VERDANT_SPAWNERS_DEFAULT_VALUE = true;
	public static final double VERDANT_SPAWNER_CHANCE_DEFAULT_VALUE = 80;
	//? if >= 1.21 {
	public static final boolean ENABLE_VERDANT_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE = true;
	//?}

	public static final boolean ENABLE_BETTER_STRAY_SPAWNS_DEFAULT_VALUE = true;
	public static final double STRAY_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int STRAY_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_STRAY_SPAWNERS_DEFAULT_VALUE = true;
	public static final double STRAY_SPAWNER_CHANCE_DEFAULT_VALUE = 80;

	public static final boolean ENABLE_BETTER_HUSK_SPAWNS_DEFAULT_VALUE = true;
	public static final double HUSK_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int HUSK_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_HUSK_SPAWNERS_DEFAULT_VALUE = true;
	public static final double HUSK_SPAWNER_CHANCE_DEFAULT_VALUE = 80;

	//? if >= 1.20.6 {
	public static final boolean ENABLE_BETTER_BOGGED_SPAWNS_DEFAULT_VALUE = true;
	public static final double BOGGED_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int BOGGED_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_BOGGED_SPAWNERS_DEFAULT_VALUE = true;
	public static final double BOGGED_SPAWNER_CHANCE_DEFAULT_VALUE = 80;
	//?}

	//? if >= 1.21.11 {
	public static final boolean ENABLE_BETTER_PARCHED_SPAWNS_DEFAULT_VALUE = true;
	public static final double PARCHED_SPAWN_CHANCE_DEFAULT_VALUE = 80;
	public static final int PARCHED_MINIMUM_Y_LEVEL_DEFAULT_VALUE = -64;
	public static final boolean ENABLE_PARCHED_SPAWNERS_DEFAULT_VALUE = true;
	public static final double PARCHED_SPAWNER_CHANCE_DEFAULT_VALUE = 80;
	//?}

	public boolean enableGelid = ENABLE_GELID_DEFAULT_VALUE;
	public boolean enableGelidSpawns = ENABLE_GELID_SPAWNS_DEFAULT_VALUE;
	public double gelidSpawnChance = GELID_SPAWN_CHANCE_DEFAULT_VALUE;
	public int gelidMinimumYLevel = GELID_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableGelidSpawners = ENABLE_GELID_SPAWNERS_DEFAULT_VALUE;
	public double gelidSpawnerChance = GELID_SPAWNER_CHANCE_DEFAULT_VALUE;
	//? if >= 1.21 {
	public boolean enableGelidSpawnersInTrialChambers = ENABLE_GELID_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE;
	//?}

	public boolean enableMurk = ENABLE_MURK_DEFAULT_VALUE;
	public boolean enableMurkSpawns = ENABLE_MURK_SPAWNS_DEFAULT_VALUE;
	public int murkSpawnWeight = MURK_SPAWN_WEIGHT_DEFAULT_VALUE;
	public int murkSpawnMinGroupSize = MURK_SPAWN_MIN_GROUP_SIZE_DEFAULT_VALUE;
	public int murkSpawnMaxGroupSize = MURK_SPAWN_MAX_GROUP_SIZE_DEFAULT_VALUE;
	//? if >= 1.21 {
	public boolean enableMurkSpawnersInTrialChambers = ENABLE_MURK_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE;
	//?}

	public boolean enableThicket = ENABLE_THICKET_DEFAULT_VALUE;
	public boolean enableThicketSpawns = ENABLE_THICKET_SPAWNS_DEFAULT_VALUE;
	public double thicketSpawnChance = THICKET_SPAWN_CHANCE_DEFAULT_VALUE;
	public int thicketMinimumYLevel = THICKET_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableThicketSpawners = ENABLE_THICKET_SPAWNERS_DEFAULT_VALUE;
	public double thicketSpawnerChance = THICKET_SPAWNER_CHANCE_DEFAULT_VALUE;
	public boolean enableThicketSpawnersInTrialChambers = ENABLE_THICKET_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE;

	public boolean enableVerdant = ENABLE_VERDANT_DEFAULT_VALUE;
	public boolean enableVerdantSpawns = ENABLE_VERDANT_SPAWNS_DEFAULT_VALUE;
	public double verdantSpawnChance = VERDANT_SPAWN_CHANCE_DEFAULT_VALUE;
	public int verdantMinimumYLevel = VERDANT_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableVerdantSpawners = ENABLE_VERDANT_SPAWNERS_DEFAULT_VALUE;
	public double verdantSpawnerChance = VERDANT_SPAWNER_CHANCE_DEFAULT_VALUE;
	//? if >= 1.21 {
	public boolean enableVerdantSpawnersInTrialChambers = ENABLE_VERDANT_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE;
	//?}

	public boolean enableBetterStraySpawns = ENABLE_BETTER_STRAY_SPAWNS_DEFAULT_VALUE;
	public double straySpawnChance = STRAY_SPAWN_CHANCE_DEFAULT_VALUE;
	public int strayMinimumYLevel = STRAY_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableStraySpawners = ENABLE_STRAY_SPAWNERS_DEFAULT_VALUE;
	public double straySpawnerChance = STRAY_SPAWNER_CHANCE_DEFAULT_VALUE;

	public boolean enableBetterHuskSpawns = ENABLE_BETTER_HUSK_SPAWNS_DEFAULT_VALUE;
	public double huskSpawnChance = HUSK_SPAWN_CHANCE_DEFAULT_VALUE;
	public int huskMinimumYLevel = HUSK_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableHuskSpawners = ENABLE_HUSK_SPAWNERS_DEFAULT_VALUE;
	public double huskSpawnerChance = HUSK_SPAWNER_CHANCE_DEFAULT_VALUE;

	//? if >= 1.20.6 {
	public boolean enableBetterBoggedSpawns = ENABLE_BETTER_BOGGED_SPAWNS_DEFAULT_VALUE;
	public double boggedSpawnChance = BOGGED_SPAWN_CHANCE_DEFAULT_VALUE;
	public int boggedMinimumYLevel = BOGGED_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableBoggedSpawners = ENABLE_BOGGED_SPAWNERS_DEFAULT_VALUE;
	public double boggedSpawnerChance = BOGGED_SPAWNER_CHANCE_DEFAULT_VALUE;
	//?}

	//? if >= 1.21.11 {
	public boolean enableBetterParchedSpawns = ENABLE_BETTER_PARCHED_SPAWNS_DEFAULT_VALUE;
	public double parchedSpawnChance = PARCHED_SPAWN_CHANCE_DEFAULT_VALUE;
	public int parchedMinimumYLevel = PARCHED_MINIMUM_Y_LEVEL_DEFAULT_VALUE;
	public boolean enableParchedSpawners = ENABLE_PARCHED_SPAWNERS_DEFAULT_VALUE;
	public double parchedSpawnerChance = PARCHED_SPAWNER_CHANCE_DEFAULT_VALUE;
	//?}

	public static void load() {
		VariantsAndVenturesConfigSerializer.load();
	}

	public static void save() {
		VariantsAndVenturesConfigSerializer.save();
	}
}
