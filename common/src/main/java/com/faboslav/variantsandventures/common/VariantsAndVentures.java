package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.config.VariantsAndVenturesConfig;
import com.faboslav.variantsandventures.common.entity.event.*;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.AddSpawnBiomeModificationsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntitySpawnRestrictionsEvent;
import com.faboslav.variantsandventures.common.init.*;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VariantsAndVentures
{
	public static final String MOD_ID = "variantsandventures";
	private static final Logger LOGGER = LoggerFactory.getLogger(VariantsAndVentures.MOD_ID);
	private static final VariantsAndVenturesConfig CONFIG = new VariantsAndVenturesConfig();

	public static ResourceLocation makeID(String path) {
		return ResourceLocation.fromNamespaceAndPath(
			MOD_ID,
			path
		);
	}

	public static String makeStringID(String name) {
		return MOD_ID + ":" + name;
	}

	public static ResourceLocation makeNamespacedId(String id) {
		return ResourceLocation.tryParse(
			id
		);
	}

	public static VariantsAndVenturesConfig getConfig() {
		return CONFIG;
	}

	public static Logger getLogger() {
		return LOGGER;
	}


	public static void init() {
		VariantsAndVentures.getConfig().load();
		VariantsAndVenturesTags.init();
		initEvents();
		initRegistries();
	}

	public static void lateInit() {
	}

	private static void initEvents() {
		EntitySpawnEvent.EVENT.addListener(GelidOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(HuskOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(StrayOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(BoggedOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(ThicketOnEntitySpawn::handleEntitySpawn);
		EntitySpawnEvent.EVENT.addListener(VerdantOnEntitySpawn::handleEntitySpawn);
		ProjectileHitEvent.EVENT.addListener(GelidOnSnowballHitEvent::handleSnowballHit);
		RegisterEntityAttributesEvent.EVENT.addListener(VariantsAndVenturesEntityTypes::registerEntityAttributes);
		RegisterEntitySpawnRestrictionsEvent.EVENT.addListener(VariantsAndVenturesEntityTypes::registerEntitySpawnRestrictions);
		AddSpawnBiomeModificationsEvent.EVENT.addListener(VariantsAndVenturesEntityTypes::addSpawnBiomeModifications);
		AddItemGroupEntriesEvent.EVENT.addListener(VariantsAndVenturesItemGroups::addItemGroupEntries);
	}

	private static void initRegistries() {
		VariantsAndVenturesItems.ITEMS.init();
		VariantsAndVenturesBlocks.BLOCKS.init();
		VariantsAndVenturesEntityTypes.ENTITY_TYPES.init();
		VariantsAndVenturesItemGroups.ITEM_GROUPS.init();
		VariantsAndVenturesSoundEvents.SOUND_EVENTS.init();
	}
}
