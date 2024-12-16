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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import java.util.function.Supplier;

/**
 * @see EntityType
 */
public final class VariantsAndVenturesEntityTypes {
    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.ENTITY_TYPE, VariantsAndVentures.MOD_ID);
    public static boolean previousUseChoiceTypeRegistrations = SharedConstants.CHECK_DATA_FIXER_SCHEMA;

    public static final Supplier<EntityType<GelidEntity>> GELID;
    public static final Supplier<EntityType<MurkEntity>> MURK;
    public static final Supplier<EntityType<ThicketEntity>> THICKET;
    public static final Supplier<EntityType<VerdantEntity>> VERDANT;

    static {
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
        GELID = ENTITY_TYPES.register("gelid", () -> EntityType.Builder.of(GelidEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8).build(VariantsAndVentures.makeStringID("gelid")));
        MURK = ENTITY_TYPES.register("murk", () -> EntityType.Builder.of(MurkEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(VariantsAndVentures.makeStringID("murk")));
        THICKET = ENTITY_TYPES.register("thicket", () -> EntityType.Builder.of(ThicketEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(VariantsAndVentures.makeStringID("thicket")));
        VERDANT = ENTITY_TYPES.register("verdant", () -> EntityType.Builder.of(VerdantEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(VariantsAndVentures.makeStringID("verdant")));
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = previousUseChoiceTypeRegistrations;
    }

    public static void registerEntityAttributes(RegisterEntityAttributesEvent event) {
        event.register(VariantsAndVenturesEntityTypes.GELID.get(), GelidEntity.createGelidAttributes());
        event.register(VariantsAndVenturesEntityTypes.MURK.get(), MurkEntity.createMurkAttributes());
        event.register(VariantsAndVenturesEntityTypes.THICKET.get(), ThicketEntity.createAttributes());
        event.register(VariantsAndVenturesEntityTypes.VERDANT.get(), VerdantEntity.createAttributes());
    }

    public static void registerEntitySpawnRestrictions(RegisterEntitySpawnRestrictionsEvent event) {
        event.register(MURK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MurkEntity::canSpawn);
    }

    public static void addSpawnBiomeModifications(AddSpawnBiomeModificationsEvent event) {
        if (VariantsAndVentures.getConfig().enableMurk && VariantsAndVentures.getConfig().enableMurkSpawns) {
            event.add(VariantsAndVenturesTags.HAS_MURK, MobCategory.MONSTER, MURK.get(), 4, 1, 1);
        }
    }
}
