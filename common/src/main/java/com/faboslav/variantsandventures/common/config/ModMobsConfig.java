package com.faboslav.variantsandventures.common.config;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.DoubleSlider;
import dev.isxander.yacl3.config.v2.api.autogen.IntSlider;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import java.nio.file.Path;

public final class ModMobsConfig
{
	public static ConfigClassHandler<ModMobsConfig> HANDLER = ConfigClassHandler.createBuilder(ModMobsConfig.class)
		.id(VariantsAndVentures.makeID(VariantsAndVentures.MOD_ID))
		.serializer(config -> GsonConfigSerializerBuilder.create(config)
			.appendGsonBuilder(GsonBuilder::setPrettyPrinting)
			.setPath(Path.of("config", VariantsAndVentures.MOD_ID + "/mod_mobs.json")).build()
		)
		.build();

	private static final String MOD_MOBS_CATEGORY = "mod_mobs";
	private static final String GELID_GROUP = "gelid";
	private static final String MURK_GROUP = "murk";
	private static final String THICKET_GROUP = "thicket";
	private static final String VERDANT_GROUP = "verdant";

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
}
