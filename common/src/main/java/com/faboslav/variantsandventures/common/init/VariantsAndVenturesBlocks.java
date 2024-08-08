package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;

/**
 * @see Blocks
 */
public final class VariantsAndVenturesBlocks
{
	public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registry.BLOCK, VariantsAndVentures.MOD_ID);

	private VariantsAndVenturesBlocks() {
	}
}
