package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.color.world.FoliageColors;
import com.faboslav.variantsandventures.common.events.client.RegisterItemColorEvent;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

/**
 * @see Items
 */
public final class VariantsAndVenturesItems
{
	public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(Registry.ITEM, VariantsAndVentures.MOD_ID);

	public final static RegistryEntry<Item> CHORUS_SAPLING = ITEMS.register("chorus_sapling", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_SAPLING.get(), (new Item.Settings()).group(ItemGroup.DECORATIONS)));
	public final static RegistryEntry<Item> CHORUS_LEAVES = ITEMS.register("chorus_leaves", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_LEAVES.get(), (new Item.Settings()).group(ItemGroup.DECORATIONS)));
	public final static RegistryEntry<Item> CHORUS_LOG = ITEMS.register("chorus_log", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_LOG.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> STRIPPED_CHORUS_LOG = ITEMS.register("stripped_chorus_log", () -> new BlockItem(VariantsAndVenturesBlocks.STRIPPED_CHORUS_LOG.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> CHORUS_WOOD = ITEMS.register("chorus_wood", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_WOOD.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> STRIPPED_CHORUS_WOOD = ITEMS.register("stripped_chorus_wood", () -> new BlockItem(VariantsAndVenturesBlocks.STRIPPED_CHORUS_WOOD.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));

	public final static RegistryEntry<Item> CHORUS_PLANKS = ITEMS.register("chorus_planks", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_PLANKS.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> CHORUS_SLAB = ITEMS.register("chorus_slab", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_SLAB.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> CHORUS_PRESSURE_PLATE = ITEMS.register("chorus_pressure_plate", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_PRESSURE_PLATE.get(), (new Item.Settings()).group(ItemGroup.REDSTONE)));
	public final static RegistryEntry<Item> CHORUS_FENCE = ITEMS.register("chorus_fence", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_FENCE.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> CHORUS_TRAPDOOR = ITEMS.register("chorus_trapdoor", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_TRAPDOOR.get(), (new Item.Settings()).group(ItemGroup.REDSTONE)));
	public final static RegistryEntry<Item> CHORUS_FENCE_GATE = ITEMS.register("chorus_fence_gate", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_FENCE_GATE.get(), (new Item.Settings()).group(ItemGroup.REDSTONE)));
	public final static RegistryEntry<Item> CHORUS_STAIRS = ITEMS.register("chorus_stairs", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_STAIRS.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> CHORUS_BUTTON = ITEMS.register("chorus_button", () -> new BlockItem(VariantsAndVenturesBlocks.CHORUS_BUTTON.get(), (new Item.Settings()).group(ItemGroup.REDSTONE)));
	public final static RegistryEntry<Item> CHORUS_DOOR = ITEMS.register("chorus_door", () -> (new TallBlockItem(VariantsAndVenturesBlocks.CHORUS_DOOR.get(), (new Item.Settings()).group(ItemGroup.REDSTONE))));
	public final static RegistryEntry<Item> CHORUS_SIGN = ITEMS.register("chorus_sign", () -> (new SignItem((new Item.Settings()).maxCount(16).group(ItemGroup.DECORATIONS), VariantsAndVenturesBlocks.CHORUS_SIGN.get(), VariantsAndVenturesBlocks.CHORUS_WALL_SIGN.get())));

	public final static RegistryEntry<Item> END_DIRT = ITEMS.register("end_dirt", () -> new BlockItem(VariantsAndVenturesBlocks.END_DIRT.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> END_GRASS_BLOCK = ITEMS.register("end_grass_block", () -> new BlockItem(VariantsAndVenturesBlocks.END_GRASS_BLOCK.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> END_GRASS = ITEMS.register("end_grass", () -> new BlockItem(VariantsAndVenturesBlocks.END_GRASS.get(), (new Item.Settings()).group(ItemGroup.DECORATIONS)));
	public final static RegistryEntry<Item> END_FERN = ITEMS.register("end_fern", () -> new BlockItem(VariantsAndVenturesBlocks.END_FERN.get(), (new Item.Settings()).group(ItemGroup.DECORATIONS)));
	public final static RegistryEntry<Item> LARGE_END_FERN = ITEMS.register("large_end_fern", () -> new BlockItem(VariantsAndVenturesBlocks.LARGE_END_FERN.get(), (new Item.Settings()).group(ItemGroup.DECORATIONS)));

	public final static RegistryEntry<Item> END_STONE_PILLAR = ITEMS.register("end_stone_pillar", () -> new BlockItem(VariantsAndVenturesBlocks.END_STONE_PILLAR.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> CHISELED_END_STONE = ITEMS.register("chiseled_end_stone", () -> new BlockItem(VariantsAndVenturesBlocks.CHISELED_END_STONE.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));
	public final static RegistryEntry<Item> SMOOTH_END_STONE = ITEMS.register("smooth_end_stone", () -> new BlockItem(VariantsAndVenturesBlocks.SMOOTH_END_STONE.get(), (new Item.Settings()).group(ItemGroup.BUILDING_BLOCKS)));

	public final static RegistryEntry<Item> GELID_SPAWN_EGG = ITEMS.register("gelid_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.GELID, 0x108E9C, 0xDEF7F7, new Item.Settings().maxCount(64).group(ItemGroup.MISC)));
	public final static RegistryEntry<Item> MURK_SPAWN_EGG = ITEMS.register("murk_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.MURK, 12698049, 0xA276A9, new Item.Settings().maxCount(64).group(ItemGroup.MISC)));
	public final static RegistryEntry<Item> THICKET_SPAWN_EGG = ITEMS.register("thicket_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.THICKET, 0x005B57, 0x2B4913, new Item.Settings().maxCount(64).group(ItemGroup.MISC)));
	public final static RegistryEntry<Item> VERDANT_SPAWN_EGG = ITEMS.register("verdant_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.VERDANT, 0x8B8A75, 0x4A5D21, new Item.Settings().maxCount(64).group(ItemGroup.MISC)));

	public static void registerItemColors(RegisterItemColorEvent event) {
		event.register((stack, tintIndex) -> FoliageColors.getChorusColor(), VariantsAndVenturesBlocks.CHORUS_LEAVES.get());
	}

	private VariantsAndVenturesItems() {
	}
}
