package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import com.faboslav.variantsandventures.common.events.lifecycle.AddSpawnBiomeModificationsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntitySpawnRestrictionsEvent;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import net.minecraft.SharedConstants;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.Registries;
import net.minecraft.world.Heightmap;

import java.util.function.Supplier;

/**
 * @see EntityType
 */
public final class VariantsAndVenturesEntityTypes
{
	public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(Registries.ENTITY_TYPE, VariantsAndVentures.MOD_ID);
	public static boolean previousUseChoiceTypeRegistrations = SharedConstants.useChoiceTypeRegistrations;

	public static final Supplier<EntityType<GelidEntity>> GELID;
	public static final Supplier<EntityType<MurkEntity>> MURK;
	public static final Supplier<EntityType<ThicketEntity>> THICKET;
	public static final Supplier<EntityType<VerdantEntity>> VERDANT;

	static {
		SharedConstants.useChoiceTypeRegistrations = false;
		GELID = ENTITY_TYPES.register("gelid", () -> EntityType.Builder.create(GelidEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).allowSpawningInside(Blocks.POWDER_SNOW).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("gelid")));
		MURK = ENTITY_TYPES.register("murk", () -> EntityType.Builder.create(MurkEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.99F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("murk")));
		THICKET = ENTITY_TYPES.register("thicket", () -> EntityType.Builder.create(ThicketEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("thicket")));
		VERDANT = ENTITY_TYPES.register("verdant", () -> EntityType.Builder.create(VerdantEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.99F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("verdant")));
		SharedConstants.useChoiceTypeRegistrations = previousUseChoiceTypeRegistrations;
	}

	public static void registerEntityAttributes(RegisterEntityAttributesEvent event) {
		event.register(VariantsAndVenturesEntityTypes.GELID.get(), GelidEntity.createGelidAttributes());
		event.register(VariantsAndVenturesEntityTypes.MURK.get(), MurkEntity.createMurkAttributes());
		event.register(VariantsAndVenturesEntityTypes.THICKET.get(), ThicketEntity.createZombieAttributes());
		event.register(VariantsAndVenturesEntityTypes.VERDANT.get(), VerdantEntity.createAbstractSkeletonAttributes());
	}

	public static void registerEntitySpawnRestrictions(RegisterEntitySpawnRestrictionsEvent event) {
		event.register(MURK.get(), SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MurkEntity::canSpawn);
	}

	public static void addSpawnBiomeModifications(AddSpawnBiomeModificationsEvent event) {
		if (VariantsAndVentures.getConfig().enableMurk && VariantsAndVentures.getConfig().enableMurkSpawns) {
			event.add(VariantsAndVenturesTags.HAS_MURK, SpawnGroup.MONSTER, MURK.get(), 4, 1, 1);
		}
	}
}
