package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import com.faboslav.variantsandventures.common.platform.EntityAttributeRegistry;
import com.faboslav.variantsandventures.common.platform.EntityTypeRegistry;
import net.minecraft.SharedConstants;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import java.util.function.Supplier;

/**
 * @see EntityType
 */
public final class VariantsAndVenturesEntityType
{
	public static boolean previousUseChoiceTypeRegistrations = SharedConstants.useChoiceTypeRegistrations;

	public static final Supplier<EntityType<GelidEntity>> GELID;
	public static final Supplier<EntityType<ThicketEntity>> THICKET;
	public static final Supplier<EntityType<VerdantEntity>> VERDANT;

	static {
		SharedConstants.useChoiceTypeRegistrations = false;
		GELID = EntityTypeRegistry.register("gelid", () -> EntityType.Builder.create(GelidEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).allowSpawningInside(Blocks.POWDER_SNOW).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("gelid")));
		THICKET = EntityTypeRegistry.register("thicket", () -> EntityType.Builder.create(ThicketEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("thicket")));
		VERDANT = EntityTypeRegistry.register("verdant", () -> EntityType.Builder.create(VerdantEntity::new, SpawnGroup.MONSTER).setDimensions(0.6F, 1.99F).maxTrackingRange(8).build(VariantsAndVentures.makeStringID("verdant")));
		SharedConstants.useChoiceTypeRegistrations = previousUseChoiceTypeRegistrations;
	}

	public static void init() {
		createMobAttributes();
	}

	public static void createMobAttributes() {
		EntityAttributeRegistry.register(VariantsAndVenturesEntityType.GELID, GelidEntity::createZombieAttributes);
		EntityAttributeRegistry.register(VariantsAndVenturesEntityType.THICKET, GelidEntity::createZombieAttributes);
		EntityAttributeRegistry.register(VariantsAndVenturesEntityType.VERDANT, VerdantEntity::createAbstractSkeletonAttributes);
	}
}
