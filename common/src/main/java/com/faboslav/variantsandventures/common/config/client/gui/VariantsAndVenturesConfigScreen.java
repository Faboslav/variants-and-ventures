package com.faboslav.variantsandventures.common.config.client.gui;

import net.minecraft.client.gui.screens.Screen;

//? if yacl {
import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.config.VariantsAndVenturesConfig;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.DoubleSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;
import java.util.function.Supplier;
//?}

public final class VariantsAndVenturesConfigScreen
{
	public Screen generateScreen(Screen parent) {
		//? if yacl {
		var config = VariantsAndVentures.getConfig();

		return YetAnotherConfigLib.createBuilder()
			.title(Component.translatable("yacl3.config.variantsandventures:variantsandventures"))
			.category(ConfigCategory.createBuilder()
				.name(Component.translatable("yacl3.config.variantsandventures:variantsandventures.category.mod_mobs"))
				.group(group("mod_mobs", "gelid")
					.option(bool("enableGelid", VariantsAndVenturesConfig.ENABLE_GELID_DEFAULT_VALUE, () -> config.enableGelid, value -> config.enableGelid = value))
					.option(bool("enableGelidSpawns", VariantsAndVenturesConfig.ENABLE_GELID_SPAWNS_DEFAULT_VALUE, () -> config.enableGelidSpawns, value -> config.enableGelidSpawns = value))
					.option(percentSlider("gelidSpawnChance", VariantsAndVenturesConfig.GELID_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.gelidSpawnChance, value -> config.gelidSpawnChance = value))
					.option(slider("gelidMinimumYLevel", VariantsAndVenturesConfig.GELID_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.gelidMinimumYLevel, value -> config.gelidMinimumYLevel = value, -256, 256))
					.option(bool("enableGelidSpawners", VariantsAndVenturesConfig.ENABLE_GELID_SPAWNERS_DEFAULT_VALUE, () -> config.enableGelidSpawners, value -> config.enableGelidSpawners = value))
					.option(percentSlider("gelidSpawnerChance", VariantsAndVenturesConfig.GELID_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.gelidSpawnerChance, value -> config.gelidSpawnerChance = value))
					//? if >= 1.21 {
					.option(bool("enableGelidSpawnersInTrialChambers", VariantsAndVenturesConfig.ENABLE_GELID_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE, () -> config.enableGelidSpawnersInTrialChambers, value -> config.enableGelidSpawnersInTrialChambers = value))
					//?}
					.build())
				.group(group("mod_mobs", "murk")
					.option(bool("enableMurk", VariantsAndVenturesConfig.ENABLE_MURK_DEFAULT_VALUE, () -> config.enableMurk, value -> config.enableMurk = value))
					.option(bool("enableMurkSpawns", VariantsAndVenturesConfig.ENABLE_MURK_SPAWNS_DEFAULT_VALUE, () -> config.enableMurkSpawns, value -> config.enableMurkSpawns = value))
					.option(slider("murkSpawnWeight", VariantsAndVenturesConfig.MURK_SPAWN_WEIGHT_DEFAULT_VALUE, () -> config.murkSpawnWeight, value -> config.murkSpawnWeight = value, 0, 100))
					.option(slider("murkSpawnMinGroupSize", VariantsAndVenturesConfig.MURK_SPAWN_MIN_GROUP_SIZE_DEFAULT_VALUE, () -> config.murkSpawnMinGroupSize, value -> config.murkSpawnMinGroupSize = value, 0, 100))
					.option(slider("murkSpawnMaxGroupSize", VariantsAndVenturesConfig.MURK_SPAWN_MAX_GROUP_SIZE_DEFAULT_VALUE, () -> config.murkSpawnMaxGroupSize, value -> config.murkSpawnMaxGroupSize = value, 0, 100))
					//? if >= 1.21 {
					.option(bool("enableMurkSpawnersInTrialChambers", VariantsAndVenturesConfig.ENABLE_MURK_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE, () -> config.enableMurkSpawnersInTrialChambers, value -> config.enableMurkSpawnersInTrialChambers = value))
					//?}
					.build())
				.group(group("mod_mobs", "thicket")
					.option(bool("enableThicket", VariantsAndVenturesConfig.ENABLE_THICKET_DEFAULT_VALUE, () -> config.enableThicket, value -> config.enableThicket = value))
					.option(bool("enableThicketSpawns", VariantsAndVenturesConfig.ENABLE_THICKET_SPAWNS_DEFAULT_VALUE, () -> config.enableThicketSpawns, value -> config.enableThicketSpawns = value))
					.option(percentSlider("thicketSpawnChance", VariantsAndVenturesConfig.THICKET_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.thicketSpawnChance, value -> config.thicketSpawnChance = value))
					.option(slider("thicketMinimumYLevel", VariantsAndVenturesConfig.THICKET_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.thicketMinimumYLevel, value -> config.thicketMinimumYLevel = value, -256, 256))
					.option(bool("enableThicketSpawners", VariantsAndVenturesConfig.ENABLE_THICKET_SPAWNERS_DEFAULT_VALUE, () -> config.enableThicketSpawners, value -> config.enableThicketSpawners = value))
					.option(percentSlider("thicketSpawnerChance", VariantsAndVenturesConfig.THICKET_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.thicketSpawnerChance, value -> config.thicketSpawnerChance = value))
					.option(bool("enableThicketSpawnersInTrialChambers", VariantsAndVenturesConfig.ENABLE_THICKET_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE, () -> config.enableThicketSpawnersInTrialChambers, value -> config.enableThicketSpawnersInTrialChambers = value))
					.build())
				.group(group("mod_mobs", "verdant")
					.option(bool("enableVerdant", VariantsAndVenturesConfig.ENABLE_VERDANT_DEFAULT_VALUE, () -> config.enableVerdant, value -> config.enableVerdant = value))
					.option(bool("enableVerdantSpawns", VariantsAndVenturesConfig.ENABLE_VERDANT_SPAWNS_DEFAULT_VALUE, () -> config.enableVerdantSpawns, value -> config.enableVerdantSpawns = value))
					.option(percentSlider("verdantSpawnChance", VariantsAndVenturesConfig.VERDANT_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.verdantSpawnChance, value -> config.verdantSpawnChance = value))
					.option(slider("verdantMinimumYLevel", VariantsAndVenturesConfig.VERDANT_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.verdantMinimumYLevel, value -> config.verdantMinimumYLevel = value, -256, 256))
					.option(bool("enableVerdantSpawners", VariantsAndVenturesConfig.ENABLE_VERDANT_SPAWNERS_DEFAULT_VALUE, () -> config.enableVerdantSpawners, value -> config.enableVerdantSpawners = value))
					.option(percentSlider("verdantSpawnerChance", VariantsAndVenturesConfig.VERDANT_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.verdantSpawnerChance, value -> config.verdantSpawnerChance = value))
					//? if >= 1.21 {
					.option(bool("enableVerdantSpawnersInTrialChambers", VariantsAndVenturesConfig.ENABLE_VERDANT_SPAWNERS_IN_TRIAL_CHAMBERS_DEFAULT_VALUE, () -> config.enableVerdantSpawnersInTrialChambers, value -> config.enableVerdantSpawnersInTrialChambers = value))
					//?}
					.build())
				.build())
			.category(ConfigCategory.createBuilder()
				.name(Component.translatable("yacl3.config.variantsandventures:variantsandventures.category.vanilla_mobs"))
				.group(group("vanilla_mobs", "stray")
					.option(bool("enableBetterStraySpawns", VariantsAndVenturesConfig.ENABLE_BETTER_STRAY_SPAWNS_DEFAULT_VALUE, () -> config.enableBetterStraySpawns, value -> config.enableBetterStraySpawns = value))
					.option(percentSlider("straySpawnChance", VariantsAndVenturesConfig.STRAY_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.straySpawnChance, value -> config.straySpawnChance = value))
					.option(slider("strayMinimumYLevel", VariantsAndVenturesConfig.STRAY_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.strayMinimumYLevel, value -> config.strayMinimumYLevel = value, -256, 256))
					.option(bool("enableStraySpawners", VariantsAndVenturesConfig.ENABLE_STRAY_SPAWNERS_DEFAULT_VALUE, () -> config.enableStraySpawners, value -> config.enableStraySpawners = value))
					.option(percentSlider("straySpawnerChance", VariantsAndVenturesConfig.STRAY_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.straySpawnerChance, value -> config.straySpawnerChance = value))
					.build())
				.group(group("vanilla_mobs", "husk")
					.option(bool("enableBetterHuskSpawns", VariantsAndVenturesConfig.ENABLE_BETTER_HUSK_SPAWNS_DEFAULT_VALUE, () -> config.enableBetterHuskSpawns, value -> config.enableBetterHuskSpawns = value))
					.option(percentSlider("huskSpawnChance", VariantsAndVenturesConfig.HUSK_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.huskSpawnChance, value -> config.huskSpawnChance = value))
					.option(slider("huskMinimumYLevel", VariantsAndVenturesConfig.HUSK_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.huskMinimumYLevel, value -> config.huskMinimumYLevel = value, -256, 256))
					.option(bool("enableHuskSpawners", VariantsAndVenturesConfig.ENABLE_HUSK_SPAWNERS_DEFAULT_VALUE, () -> config.enableHuskSpawners, value -> config.enableHuskSpawners = value))
					.option(percentSlider("huskSpawnerChance", VariantsAndVenturesConfig.HUSK_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.huskSpawnerChance, value -> config.huskSpawnerChance = value))
					.build())
				//? if >= 1.20.6 {
				.group(group("vanilla_mobs", "bogged")
					.option(bool("enableBetterBoggedSpawns", VariantsAndVenturesConfig.ENABLE_BETTER_BOGGED_SPAWNS_DEFAULT_VALUE, () -> config.enableBetterBoggedSpawns, value -> config.enableBetterBoggedSpawns = value))
					.option(percentSlider("boggedSpawnChance", VariantsAndVenturesConfig.BOGGED_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.boggedSpawnChance, value -> config.boggedSpawnChance = value))
					.option(slider("boggedMinimumYLevel", VariantsAndVenturesConfig.BOGGED_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.boggedMinimumYLevel, value -> config.boggedMinimumYLevel = value, -256, 256))
					.option(bool("enableBoggedSpawners", VariantsAndVenturesConfig.ENABLE_BOGGED_SPAWNERS_DEFAULT_VALUE, () -> config.enableBoggedSpawners, value -> config.enableBoggedSpawners = value))
					.option(percentSlider("boggedSpawnerChance", VariantsAndVenturesConfig.BOGGED_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.boggedSpawnerChance, value -> config.boggedSpawnerChance = value))
					.build())
				//?}
				//? if >= 1.21.11 {
				.group(group("vanilla_mobs", "parched")
					.option(bool("enableBetterParchedSpawns", VariantsAndVenturesConfig.ENABLE_BETTER_PARCHED_SPAWNS_DEFAULT_VALUE, () -> config.enableBetterParchedSpawns, value -> config.enableBetterParchedSpawns = value))
					.option(percentSlider("parchedSpawnChance", VariantsAndVenturesConfig.PARCHED_SPAWN_CHANCE_DEFAULT_VALUE, () -> config.parchedSpawnChance, value -> config.parchedSpawnChance = value))
					.option(slider("parchedMinimumYLevel", VariantsAndVenturesConfig.PARCHED_MINIMUM_Y_LEVEL_DEFAULT_VALUE, () -> config.parchedMinimumYLevel, value -> config.parchedMinimumYLevel = value, -256, 256))
					.option(bool("enableParchedSpawners", VariantsAndVenturesConfig.ENABLE_PARCHED_SPAWNERS_DEFAULT_VALUE, () -> config.enableParchedSpawners, value -> config.enableParchedSpawners = value))
					.option(percentSlider("parchedSpawnerChance", VariantsAndVenturesConfig.PARCHED_SPAWNER_CHANCE_DEFAULT_VALUE, () -> config.parchedSpawnerChance, value -> config.parchedSpawnerChance = value))
					.build())
				//?}
				.build())
			.save(VariantsAndVenturesConfig::save)
			.build()
			.generateScreen(parent);
		//?} else {
		/*return null;
		*///?}
	}

	//? if yacl {
	private static OptionGroup.Builder group(String category, String group) {
		return OptionGroup.createBuilder()
			.name(Component.translatable("yacl3.config.variantsandventures:variantsandventures.category." + category + ".group." + group));
	}

	private static OptionDescription description(String key) {
		String descriptionKey = "yacl3.config.variantsandventures:variantsandventures." + key + ".desc";

		if (!Language.getInstance().has(descriptionKey)) {
			return OptionDescription.EMPTY;
		}

		return OptionDescription.of(Component.translatable(descriptionKey));
	}

	private static Option<Boolean> bool(String key, boolean initialValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
		return Option.<Boolean>createBuilder()
			.name(Component.translatable("yacl3.config.variantsandventures:variantsandventures." + key))
			.description(description(key))
			.binding(initialValue, getter, setter)
			.controller(opt -> BooleanControllerBuilder.create(opt).formatValue(val -> val ? Component.literal("Yes") : Component.literal("No")).coloured(true))
			.build();
	}

	private static Option<Integer> slider(String key, int initialValue, Supplier<Integer> getter, Consumer<Integer> setter, int min, int max) {
		return Option.<Integer>createBuilder()
			.name(Component.translatable("yacl3.config.variantsandventures:variantsandventures." + key))
			.description(description(key))
			.binding(initialValue, getter, setter)
			.controller(option -> IntegerSliderControllerBuilder.create(option).range(min, max).step(1))
			.build();
	}

	private static Option<Double> percentSlider(String key, double initialValue, Supplier<Double> getter, Consumer<Double> setter) {
		return Option.<Double>createBuilder()
			.name(Component.translatable("yacl3.config.variantsandventures:variantsandventures." + key))
			.description(description(key))
			.binding(initialValue, getter, setter)
			.controller(option -> DoubleSliderControllerBuilder.create(option).range(0.0, 100.0).step(1.0).formatValue(value -> Component.literal(String.format("%.0f%%", value))))
			.build();
	}
	//?}
}
