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

public final class VanillaMobsConfig
{
	public static ConfigClassHandler<VanillaMobsConfig> HANDLER = ConfigClassHandler.createBuilder(VanillaMobsConfig.class)
		.id(VariantsAndVentures.makeID(VariantsAndVentures.MOD_ID))
		.serializer(config -> GsonConfigSerializerBuilder.create(config)
			.appendGsonBuilder(GsonBuilder::setPrettyPrinting)
			.setPath(Path.of("config", VariantsAndVentures.MOD_ID + "/vanilla_mobs.json")).build()
		)
		.build();

	private static final String VANILLA_MOBS_CATEGORY = "vanilla_mobs";
	private static final String STRAY_GROUP = "stray";
	private static final String HUSK_GROUP = "husk";
	private static final String BOGGED_GROUP = "bogged";

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
}
