package com.faboslav.variantsandventures.common.config;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.DoubleSlider;
import dev.isxander.yacl3.config.v2.api.autogen.IntSlider;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import java.nio.file.Path;

public final class VariantsAndVenturesConfig
{
	public static ConfigClassHandler<VariantsAndVenturesConfig> HANDLER = ConfigClassHandler.createBuilder(VariantsAndVenturesConfig.class)
		.id(VariantsAndVentures.makeID(VariantsAndVentures.MOD_ID))
		.serializer(config -> GsonConfigSerializerBuilder.create(config).setPath(Path.of("config", VariantsAndVentures.MOD_ID + ".json")).build())
		.build();

	public static final int MIN_PERCENT_VALUE = 0;
	public static final int MAX_PERCENT_VALUE = 100;
	public static final int PERCENT_STEP = 1;
	public static final String PERCENT_FORMAT = "%.0f%%";

	private static final String MOD_MOBS_CATEGORY = "mod_mobs";
	private static final String GELID_GROUP = "gelid";
	private static final String MURK_GROUP = "murk";
	private static final String THICKET_GROUP = "thicket";
	private static final String VERDANT_GROUP = "verdant";
	private static final String VANILLA_MOBS_CATEGORY = "vanilla_mobs";
	private static final String STRAY_GROUP = "stray";
	private static final String HUSK_GROUP = "husk";
	private static final String BOGGED_GROUP = "bogged";


	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelid = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelidSpawns = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double gelidSpawnChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int gelidMinimumYLevel = -64;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelidSpawners = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double gelidSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = GELID_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableGelidSpawnersInTrialChambers = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableMurk = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableMurkSpawns = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = MURK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableMurkSpawnersInTrialChambers = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicket = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicketSpawns = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double thicketSpawnChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int thicketMinimumYLevel = -64;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicketSpawners = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double thicketSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = THICKET_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableThicketSpawnersInTrialChambers = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdant = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdantSpawns = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double verdantSpawnChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int verdantMinimumYLevel = -64;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdantSpawners = true;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double verdantSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = MOD_MOBS_CATEGORY, group = VERDANT_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableVerdantSpawnersInTrialChambers = true;


	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBetterStraySpawns = true;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double straySpawnChance = 80;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int strayMinimumYLevel = -64;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableStraySpawners = true;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = STRAY_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double straySpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBetterHuskSpawns = true;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double huskSpawnChance = 80;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int huskMinimumYLevel = -64;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableHuskSpawners = true;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = HUSK_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double huskSpawnerChance = 80;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBetterBoggedSpawns = true;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double boggedSpawnChance = 80;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@IntSlider(min = -256, max = 256, step = 1)
	public int boggedMinimumYLevel = -64;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
	public boolean enableBoggedSpawners = true;

	@SerialEntry()
	@AutoGen(category = VANILLA_MOBS_CATEGORY, group = BOGGED_GROUP)
	@DoubleSlider(min = VariantsAndVenturesConfig.MIN_PERCENT_VALUE, max = VariantsAndVenturesConfig.MAX_PERCENT_VALUE, step = VariantsAndVenturesConfig.PERCENT_STEP, format = VariantsAndVenturesConfig.PERCENT_FORMAT)
	public double boggedSpawnerChance = 80;

	public void load() {
		HANDLER.load();
	}
}
