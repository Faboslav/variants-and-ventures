package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.mixin.TrunkPlacerTypeAccessor;
import com.faboslav.variantsandventures.common.world.gen.trunk.ArchingTrunkPlacer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class VariantsAndVenturesTrunkPlacerType
{
	public static final ResourcefulRegistry<TrunkPlacerType<?>> TRUNK_PLACER_TYPE = ResourcefulRegistries.create(Registry.TRUNK_PLACER_TYPE, VariantsAndVentures.MOD_ID);

	public static RegistryEntry<TrunkPlacerType<?>> ARCHING_TRUNK_PLACER = TRUNK_PLACER_TYPE.register("arching_trunk_placer", () -> TrunkPlacerTypeAccessor.variantsandventures$createTrunkPlacerType(ArchingTrunkPlacer.CODEC));
}
