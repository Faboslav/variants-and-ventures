package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;

import java.util.function.Supplier;

/**
 * @see EntityType
 */
public final class VariantsAndVenturesEntityTypes
{
	public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(Registries.ENTITY_TYPE, VariantsAndVentures.MOD_ID);

	public static final Supplier<EntityType<GelidEntity>> GELID = ENTITY_TYPES.register("gelid", () -> EntityType.Builder.create(GelidEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).allowSpawningInside(Blocks.POWDER_SNOW).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("gelid")));
	public static final Supplier<EntityType<ThicketEntity>> THICKET = ENTITY_TYPES.register("thicket", () -> EntityType.Builder.create(ThicketEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("thicket")));
	public static final Supplier<EntityType<VerdantEntity>> VERDANT = ENTITY_TYPES.register("verdant", () -> EntityType.Builder.create(VerdantEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.99F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("verdant")));

	public static void registerEntityAttributes(RegisterEntityAttributesEvent event) {
		event.register(VariantsAndVenturesEntityTypes.GELID.get(), GelidEntity.createGelidAttributes());
		event.register(VariantsAndVenturesEntityTypes.THICKET.get(), ThicketEntity.createZombieAttributes());
		event.register(VariantsAndVenturesEntityTypes.VERDANT.get(), VerdantEntity.createAbstractSkeletonAttributes());
	}
}
