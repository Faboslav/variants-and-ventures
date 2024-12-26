package com.faboslav.variantsandventures.common.config;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.IntSlider;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;

import java.nio.file.Path;

public final class VariantsAndVenturesConfig
{
	public static ConfigClassHandler<VariantsAndVenturesConfig> HANDLER = ConfigClassHandler.createBuilder(VariantsAndVenturesConfig.class)
		.id(VariantsAndVentures.makeID(VariantsAndVentures.MOD_ID))
		.serializer(config -> GsonConfigSerializerBuilder.create(config).setPath(Path.of("config", VariantsAndVentures.MOD_ID + ".json")).build())
		.build();

	private static final String MOD_MOBS_CATEGORY = "mod_mobs";
	private static final String VANILLA_MOBS_CATEGORY = "vanilla_mobs";
	private static final String STRAY_GROUP = "stray";
	private static final String HUSK_GROUP = "husk";
	private static final String BOGGED_GROUP = "bogged";
	private static final String GELID_GROUP = "gelid";
	private static final String MURK_GROUP = "murk";
	private static final String THICKET_GROUP = "thicket";
	private static final String VERDANT_GROUP = "verdant";

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBetterStraySpawns = true;
	// "Enable better Stray spawns"

	// @Description("Spawn chance of Stray (For example 0.8 means that stray will spawn in place of 80% of all skeletons spawned in cold biomes.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int straySpawnChance = 80;

	// @Description("Minimum Y level for Stray spawns (For example 62 means that stray will spawn only above the ocean Y level.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int strayMinimumYLevel = -64;

	// @Description("Enable spawners in dungeons")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableStraySpawners = true;

	// @Description("Chance of Stray spawner in dungeons (For example 0.8 means that stray spawner will be in place of 80% of all skeleton spawners in cold biomes.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int straySpawnerChance = 80;
	
	// @Description("Enable better Husk spawns")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBetterHuskSpawns = true;

	// @Description("Spawn chance of Husk (For example 0.8 means that husk will spawn in place of 80% of all skeletons spawned in desert biomes.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int huskSpawnChance = 80;

	// @Description("Minimum Y level for Husk spawns (For example 62 means that husk will spawn only above the ocean Y level.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int huskMinimumYLevel = -64;

	// @Description("Enable spawners in dungeons")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableHuskSpawners = true;

	// @Description("Chance of Husk spawner in dungeons (For example 0.8 means that husk spawner will be in place of 80% of all zombie spawners in desert biomes.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int huskSpawnerChance = 80;

	// @Description("Enable better Bogged spawns")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBetterBoggedSpawns = true;

	// @Description("Spawn chance of Bogged (For example 0.8 means that bogged will spawn in place of 80% of all skeletons spawned in mushroom-like biomes.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int boggedSpawnChance = 80;

	// @Description("Minimum Y level for Bogged spawns (For example 62 means that bogged will spawn only above the ocean Y level.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int boggedMinimumYLevel = -64;

	// @Description("Enable spawners in dungeons")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBoggedSpawners = true;

	// @Description("Chance of Bogged spawner in dungeons (For example 0.8 means that bogged spawner will be in place of 80% of all bogged spawners in mushroom-like biomes.")
	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int boggedSpawnerChance = 80;

	// @Category("Gelid (Frozen Zombie)")
	// @Description("Enable Gelid")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelid = true;

	// @Description("Enable Gelid spawns")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelidSpawns = true;

	// @Description("Spawn chance of Gelid (For example 0.8 means that gelid will spawn in place of 80% of all zombies spawned in cold biomes.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int gelidSpawnChance = 80;

	// @Description("Minimum Y level for Gelid spawns (For example 62 means that gelid will spawn only above the ocean Y level.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int gelidMinimumYLevel = -64;

	// @Description("Enable Gelid spawners in dungeons")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelidSpawners = true;

	// @Description("Chance of Gelid spawner in dungeons (For example 0.8 means that gelid spawner will be in place of 80% of all zombie spawners in cold biomes.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int gelidSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelidSpawnersToTrialChambers = true;

	// @Category("Murk (Sunken Skeleton)")
	// @Description("Enable Murk")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableMurk = true;

	// @Description("Enable Murk spawns")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableMurkSpawns = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableMurkSpawnersToTrialChambers = true;

	// @Category("Thicket (Jungle Zombie)")
	// @Description("Enable Thickets")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicket = true;

	// @Description("Enable Thickets spawns")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicketSpawns = true;

	// @Description("Spawn chance of Thicket (For example 0.8 means that thicket will spawn in place of 80% of all zombies spawned in cold biomes.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int thicketSpawnChance = 80;

	// @Description("Minimum Y level for Thicket spawns (For example 62 means that thicket will spawn only above the ocean Y level.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int thicketMinimumYLevel = -64;

	// @Description("Enable Thicket spawners in dungeons")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicketSpawners = true;

	// @Description("Chance of Thicket spawner in dungeons (For example 0.8 means that thicket spawner will be in place of 80% of all zombie spawners in cold biomes.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int thicketSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicketSpawnersToTrialChambers = true;

	// @Category("Verdant (Jungle Skeleton)")
	// @Description("Enable Verdant")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdant = true;

	// @Description("Enable Verdant spawns")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdantSpawns = true;

	// @Description("Spawn chance of Verdant (For example 0.8 means that verdant will spawn in place of 80% of all skeletons spawned in jungle biomes.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int verdantSpawnChance = 80;

	// @Description("Minimum Y level for Verdant spawns (For example 62 means that verdant will spawn only above the ocean Y level.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int verdantMinimumYLevel = -64;

	// @Description("Enable Verdant spawners in dungeons")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdantSpawners = true;

	// @Description("Chance of Verdant spawner in dungeons (For example 0.8 means that verdant spawner will be in place of 80% of all skeleton spawners in jungle biomes.")
	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@IntSlider(min = 0, max = 100, step = 1)
	public int verdantSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdantSpawnersToTrialChambers = true;

	public void load() {
		HANDLER.load();
	}
}
