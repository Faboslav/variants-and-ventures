package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.mixin.SkullBlockAccessor;
import com.faboslav.variantsandventures.common.mixin.WallSkullBlockAccessor;
import com.faboslav.variantsandventures.common.platform.BlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;

import java.util.function.Supplier;

/**
 * @see Blocks
 */
public final class VariantsAndVenturesBlocks
{
	public static Supplier<Block> GELID_HEAD;
	public static Supplier<Block> GELID_WALL_HEAD;
	public static Supplier<Block> THICKET_HEAD;
	public static Supplier<Block> THICKET_WALL_HEAD;
	public static Supplier<Block> VERDANT_SKULL;
	public static Supplier<Block> VERDANT_WALL_SKULL;

	static {
		GELID_HEAD = BlockRegistry.register("gelid_head", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.GELID.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F)));
		GELID_WALL_HEAD = BlockRegistry.register("gelid_wall_head", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.GELID.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).dropsLike(GELID_HEAD.get())));
		THICKET_HEAD = BlockRegistry.register("thicket_head", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.THICKET.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F)));
		THICKET_WALL_HEAD = BlockRegistry.register("thicket_wall_head", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.THICKET.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).dropsLike(THICKET_HEAD.get())));
		VERDANT_SKULL = BlockRegistry.register("verdant_skull", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.VERDANT.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F)));
		VERDANT_WALL_SKULL = BlockRegistry.register("verdant_wall_skull", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.VERDANT.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).dropsLike(VERDANT_SKULL.get())));
	}

	public static void init() {
	}

	private VariantsAndVenturesBlocks() {
	}
}
