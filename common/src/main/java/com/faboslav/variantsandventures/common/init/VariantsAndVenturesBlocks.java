package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.block.EndGrassBlock;
import com.faboslav.variantsandventures.common.client.color.world.FoliageColors;
import com.faboslav.variantsandventures.common.events.client.RegisterBlockColorEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterRenderLayersEvent;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.mixin.BlocksAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;

/**
 * @see Blocks
 */
public final class VariantsAndVenturesBlocks
{
	public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registry.BLOCK, VariantsAndVentures.MOD_ID);

	public static RegistryEntry<Block> END_DIRT = BLOCKS.register("end_dirt", () -> new EndGrassBlock(AbstractBlock.Settings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(0.5F).sounds(BlockSoundGroup.GRAVEL)));
	public static RegistryEntry<Block> END_GRASS_BLOCK = BLOCKS.register("end_grass_block", () -> new EndGrassBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS)));
	public static RegistryEntry<Block> END_GRASS = BLOCKS.register("end_grass", () -> new FernBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static RegistryEntry<Block> END_FERN = BLOCKS.register("end_fern", () -> new FernBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static RegistryEntry<Block> LARGE_END_FERN = BLOCKS.register("large_end_fern", () -> new TallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));

	public static RegistryEntry<Block> CHORUS_SAPLING = BLOCKS.register("chorus_sapling", () -> new SaplingBlock(new OakSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static RegistryEntry<Block> CHORUS_LEAVES = BLOCKS.register("chorus_leaves", () -> BlocksAccessor.variantsandventures$createLeavesBlock(BlockSoundGroup.GRASS));
	public static RegistryEntry<Block> CHORUS_LOG = BLOCKS.register("chorus_log", () -> BlocksAccessor.variantsandventures$createLogBlock(MapColor.PURPLE, MapColor.PURPLE));
	public static RegistryEntry<Block> STRIPPED_CHORUS_LOG = BLOCKS.register("stripped_chorus_log", () -> BlocksAccessor.variantsandventures$createLogBlock(MapColor.PURPLE, MapColor.PURPLE));
	public static RegistryEntry<Block> CHORUS_WOOD = BLOCKS.register("chorus_wood", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.PURPLE).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
	public static RegistryEntry<Block> STRIPPED_CHORUS_WOOD = BLOCKS.register("stripped_chorus_wood", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.PURPLE).strength(2.0F).sounds(BlockSoundGroup.WOOD)));

	// TODO end wood?
	public static RegistryEntry<Block> CHORUS_PLANKS = BLOCKS.register("chorus_planks", () -> new Block(AbstractBlock.Settings.of(Material.NETHER_WOOD, MapColor.PURPLE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
	public static RegistryEntry<Block> CHORUS_SLAB = BLOCKS.register("chorus_slab", () -> new SlabBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
	public static RegistryEntry<Block> CHORUS_PRESSURE_PLATE = BLOCKS.register("chorus_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD)));
	public static RegistryEntry<Block> CHORUS_FENCE = BLOCKS.register("chorus_fence", () -> new FenceBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
	//public static RegistryEntry<Block> CHORUS_TRAPDOOR = BLOCKS.register("chorus_trapdoor", () -> new TrapdoorBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning(Blocks::never)));
	public static RegistryEntry<Block> CHORUS_TRAPDOOR = BLOCKS.register("chorus_trapdoor", () -> new TrapdoorBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
	public static RegistryEntry<Block> CHORUS_FENCE_GATE = BLOCKS.register("chorus_fence_gate", () -> new FenceGateBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
	public static RegistryEntry<Block> CHORUS_STAIRS = BLOCKS.register("chorus_stairs", () -> new StairsBlock(CHORUS_PLANKS.get().getDefaultState(), AbstractBlock.Settings.copy(CHORUS_PLANKS.get())));
	public static RegistryEntry<Block> CHORUS_BUTTON = BLOCKS.register("chorus_button", () -> new WoodenButtonBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD)));
	public static RegistryEntry<Block> CHORUS_DOOR = BLOCKS.register("chorus_door", () -> new DoorBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).strength(3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
	public static RegistryEntry<Block> CHORUS_SIGN = BLOCKS.register("chorus_sign", () -> new SignBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), SignType.CRIMSON));
	public static RegistryEntry<Block> CHORUS_WALL_SIGN = BLOCKS.register("chorus_wall_sign", () -> new WallSignBlock(AbstractBlock.Settings.of(Material.NETHER_WOOD, CHORUS_PLANKS.get().getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(CHORUS_SIGN.get()), SignType.CRIMSON));

	public static RegistryEntry<Block> END_STONE_PILLAR = BLOCKS.register("end_stone_pillar", () -> new PillarBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.MAGENTA).requiresTool().strength(3.0f, 9.0f)));
	public static RegistryEntry<Block> CHISELED_END_STONE = BLOCKS.register("chiseled_end_stone", () -> new PillarBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.MAGENTA).requiresTool().strength(3.0f, 9.0f)));
	public static RegistryEntry<Block> SMOOTH_END_STONE = BLOCKS.register("smooth_end_stone", () -> new PillarBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.MAGENTA).requiresTool().strength(3.0f, 9.0f)));

	@Environment(EnvType.CLIENT)
	public static void registerRenderLayers(RegisterRenderLayersEvent event) {
		event.register(RenderLayer.getCutout(), VariantsAndVenturesBlocks.END_GRASS.get());
		event.register(RenderLayer.getCutout(), VariantsAndVenturesBlocks.END_FERN.get());
		event.register(RenderLayer.getCutout(), VariantsAndVenturesBlocks.LARGE_END_FERN.get());
		event.register(RenderLayer.getCutout(), VariantsAndVenturesBlocks.CHORUS_DOOR.get());
		event.register(RenderLayer.getCutout(), VariantsAndVenturesBlocks.CHORUS_TRAPDOOR.get());
	}

	@Environment(EnvType.CLIENT)
	public static void registerBlockColors(RegisterBlockColorEvent event) {
		event.register((state, reader, pos, color) -> FoliageColors.getChorusColor(), VariantsAndVenturesBlocks.CHORUS_LEAVES.get());
	}

	private VariantsAndVenturesBlocks() {
	}
}
