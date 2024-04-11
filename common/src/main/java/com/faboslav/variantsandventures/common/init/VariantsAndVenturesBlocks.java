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
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;

/**
 * @see Blocks
 */
public final class VariantsAndVenturesBlocks
{
	public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registries.BLOCK, VariantsAndVentures.MOD_ID);

	public static RegistryEntry<Block> GELID_HEAD = BLOCKS.register("gelid_head", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.GELID.get(), AbstractBlock.Settings.create().instrument(Instrument.ZOMBIE).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY)));
	public static RegistryEntry<Block> GELID_WALL_HEAD = BLOCKS.register("gelid_wall_head", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.GELID.get(), AbstractBlock.Settings.create().instrument(Instrument.ZOMBIE).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY).dropsLike(GELID_HEAD.get())));
	public static RegistryEntry<Block> MURK_SKULL = BLOCKS.register("murk_skull", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.MURK.get(), AbstractBlock.Settings.create().instrument(Instrument.SKELETON).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY)));
	public static RegistryEntry<Block> MURK_WALL_SKULL = BLOCKS.register("murk_wall_skull", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.MURK.get(), AbstractBlock.Settings.create().instrument(Instrument.SKELETON).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY).dropsLike(MURK_SKULL.get())));
	public static RegistryEntry<Block> THICKET_HEAD = BLOCKS.register("thicket_head", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.THICKET.get(), AbstractBlock.Settings.create().instrument(Instrument.ZOMBIE).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY)));
	public static RegistryEntry<Block> THICKET_WALL_HEAD = BLOCKS.register("thicket_wall_head", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.THICKET.get(), AbstractBlock.Settings.create().instrument(Instrument.ZOMBIE).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY).dropsLike(THICKET_HEAD.get())));
	public static RegistryEntry<Block> VERDANT_SKULL = BLOCKS.register("verdant_skull", () -> SkullBlockAccessor.variantsandventures$createSkullBlock(SkullBlockType.VERDANT.get(), AbstractBlock.Settings.create().instrument(Instrument.SKELETON).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY)));
	public static RegistryEntry<Block> VERDANT_WALL_SKULL = BLOCKS.register("verdant_wall_skull", () -> WallSkullBlockAccessor.variantsandventures$createWallSkullBlock(SkullBlockType.VERDANT.get(), AbstractBlock.Settings.create().instrument(Instrument.SKELETON).strength(1.0F).pistonBehavior(PistonBehavior.DESTROY).dropsLike(VERDANT_SKULL.get())));

	private VariantsAndVenturesBlocks() {
	}
}
