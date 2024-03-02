package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.config.VariantsAndVenturesConfig;
import com.faboslav.variantsandventures.common.config.omegaconfig.OmegaConfig;
import com.faboslav.variantsandventures.common.entity.event.*;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.faboslav.variantsandventures.common.init.*;
import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VariantsAndVentures
{
	public static final String MOD_ID = "variantsandventures";
	private static final Logger LOGGER = LoggerFactory.getLogger(VariantsAndVentures.MOD_ID);
	private static final VariantsAndVenturesConfig CONFIG = OmegaConfig.register(VariantsAndVenturesConfig.class);

	public static Identifier makeID(String path) {
		return new Identifier(
			MOD_ID,
			path
		);
	}

	public static String makeStringID(String name) {
		return MOD_ID + ":" + name;
	}

	public static VariantsAndVenturesConfig getConfig() {
		return CONFIG;
	}

	public static Logger getLogger() {
		return LOGGER;
	}


	public static void init() {
		VariantsAndVenturesTags.init();
		initEvents();
		initRegistries();
	}

	public static void lateInit() {
		VariantsAndVenturesBlockEntityTypes.lateInit();
	}

	private static void initEvents() {
		EntitySpawnEvent.EVENT.addListener(GelidOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(HuskOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(StrayOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(ThicketOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(VerdantOnEntitySpawn::handleEntitySpawn);
		ProjectileHitEvent.EVENT.addListener(GelidOnSnowballHitEvent::handleSnowballHit);
		RegisterEntityAttributesEvent.EVENT.addListener(VariantsAndVenturesEntityTypes::registerEntityAttributes);
		SetupEvent.EVENT.addListener(DispenserAddedSpawnEgg::onSetup);
	}

	private static void initRegistries() {
		VariantsAndVenturesItems.ITEMS.init();
		VariantsAndVenturesBlocks.BLOCKS.init();
		VariantsAndVenturesEntityTypes.ENTITY_TYPES.init();
		VariantsAndVenturesSoundEvents.SOUND_EVENTS.init();
	}
}
