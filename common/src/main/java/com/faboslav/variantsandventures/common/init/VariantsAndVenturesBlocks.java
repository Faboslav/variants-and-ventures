package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.mixin.SkullBlockAccessor;
import com.faboslav.variantsandventures.common.mixin.WallSkullBlockAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

/**
 * @see Blocks
 */
public final class VariantsAndVenturesBlocks
{
	public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registry.BLOCK, VariantsAndVentures.MOD_ID);

	public static RegistryEntry<Block> GELID_HEAD = BLOCKS.register("gelid_head", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.GELID.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F)));
	public static Supplier<Block> GELID_WALL_HEAD = BLOCKS.register("gelid_wall_head", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.GELID.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).dropsLike(GELID_HEAD.get())));
	public static Supplier<Block> THICKET_HEAD = BLOCKS.register("thicket_head", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.THICKET.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F)));
	public static Supplier<Block> THICKET_WALL_HEAD = BLOCKS.register("thicket_wall_head", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.THICKET.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).dropsLike(THICKET_HEAD.get())));
	public static Supplier<Block> VERDANT_SKULL = BLOCKS.register("verdant_skull", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.VERDANT.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F)));
	public static Supplier<Block> VERDANT_WALL_SKULL = BLOCKS.register("verdant_wall_skull", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.VERDANT.get(), AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).dropsLike(VERDANT_SKULL.get())));

	private VariantsAndVenturesBlocks() {
	}
}
