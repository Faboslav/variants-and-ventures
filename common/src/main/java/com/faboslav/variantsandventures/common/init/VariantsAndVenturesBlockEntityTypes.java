package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.mixin.BlockEntityTypeAccessor;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;

import java.util.HashSet;
import java.util.Set;

/**
 * @see BlockEntityType
 */
public final class VariantsAndVenturesBlockEntityTypes
{
	public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITIES = ResourcefulRegistries.create(Registries.BLOCK_ENTITY_TYPE, VariantsAndVentures.MOD_ID);

	private static final Set<Block> SKULLS = ImmutableList.of(
		VariantsAndVenturesBlocks.GELID_HEAD.get(),
		VariantsAndVenturesBlocks.GELID_WALL_HEAD.get(),
		VariantsAndVenturesBlocks.THICKET_HEAD.get(),
		VariantsAndVenturesBlocks.THICKET_WALL_HEAD.get(),
		VariantsAndVenturesBlocks.VERDANT_SKULL.get(),
		VariantsAndVenturesBlocks.VERDANT_WALL_SKULL.get()
	).stream().collect(ImmutableSet.toImmutableSet());

	public static void lateInit() {
		Set<Block> skullBlocks = new HashSet<>();
		skullBlocks.addAll(((BlockEntityTypeAccessor) BlockEntityType.SKULL).getBlocks());
		skullBlocks.addAll(SKULLS);
		((BlockEntityTypeAccessor) BlockEntityType.SKULL).setBlocks(skullBlocks);
	}

	private VariantsAndVenturesBlockEntityTypes() {
	}
}
