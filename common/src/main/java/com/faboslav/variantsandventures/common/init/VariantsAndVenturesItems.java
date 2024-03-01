package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import com.faboslav.variantsandventures.common.platform.ItemGroupRegistry;
import com.faboslav.variantsandventures.common.platform.ItemRegistry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

import java.util.Set;
import java.util.function.Supplier;

/**
 * @see Items
 */
public final class VariantsAndVenturesItems
{
	public final static Supplier<Item> GELID_SPAWN_EGG = ItemRegistry.register("gelid_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityType.GELID, 0x108E9C, 0xDEF7F7, new Item.Settings().maxCount(64)));
	public final static Supplier<Item> THICKET_SPAWN_EGG = ItemRegistry.register("thicket_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityType.THICKET, 0x005B57, 0x2B4913, new Item.Settings().maxCount(64)));
	public final static Supplier<Item> VERDANT_SPAWN_EGG = ItemRegistry.register("verdant_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityType.VERDANT, 0x8B8A75, 0x4A5D21, new Item.Settings().maxCount(64)));
	public final static Supplier<Item> GELID_HEAD = ItemRegistry.register("gelid_head", () -> new VerticallyAttachableBlockItem(VariantsAndVenturesBlocks.GELID_HEAD.get(), VariantsAndVenturesBlocks.GELID_WALL_HEAD.get(), (new Item.Settings()).rarity(Rarity.UNCOMMON), Direction.DOWN));
	public final static Supplier<Item> THICKET_HEAD = ItemRegistry.register("thicket_head", () -> new VerticallyAttachableBlockItem(VariantsAndVenturesBlocks.THICKET_HEAD.get(), VariantsAndVenturesBlocks.THICKET_WALL_HEAD.get(), (new Item.Settings()).rarity(Rarity.UNCOMMON), Direction.DOWN));
	public final static Supplier<Item> VERDANT_SKULL = ItemRegistry.register("verdant_skull", () -> new VerticallyAttachableBlockItem(VariantsAndVenturesBlocks.VERDANT_SKULL.get(), VariantsAndVenturesBlocks.VERDANT_WALL_SKULL.get(), (new Item.Settings()).rarity(Rarity.UNCOMMON), Direction.DOWN));

	public static final Set<Supplier<Item>> SPAWN_EGGS = ImmutableList.of(
		GELID_SPAWN_EGG,
		THICKET_SPAWN_EGG,
		VERDANT_SPAWN_EGG
	).stream().collect(ImmutableSet.toImmutableSet());

	public static void init() {
	}

	public static void lateInit() {
		addToItemGroups();
	}

	private static void addToItemGroups() {
		ItemGroupRegistry.addToItemGroupAfter(ItemGroups.SPAWN_EGGS, Items.ZOMBIE_SPAWN_EGG, VariantsAndVenturesItems.GELID_SPAWN_EGG.get());
		ItemGroupRegistry.addToItemGroupAfter(ItemGroups.SPAWN_EGGS, VariantsAndVenturesItems.GELID_SPAWN_EGG.get(), VariantsAndVenturesItems.THICKET_SPAWN_EGG.get());
		ItemGroupRegistry.addToItemGroupAfter(ItemGroups.SPAWN_EGGS, Items.SKELETON_SPAWN_EGG, VariantsAndVenturesItems.VERDANT_SPAWN_EGG.get());

		ItemGroupRegistry.addToItemGroupAfter(ItemGroups.FUNCTIONAL, Items.ZOMBIE_HEAD, VariantsAndVenturesItems.GELID_HEAD.get());
		ItemGroupRegistry.addToItemGroupAfter(ItemGroups.FUNCTIONAL, VariantsAndVenturesItems.GELID_HEAD.get(), VariantsAndVenturesItems.THICKET_HEAD.get());
		ItemGroupRegistry.addToItemGroupAfter(ItemGroups.FUNCTIONAL, Items.SKELETON_SKULL, VariantsAndVenturesItems.VERDANT_SKULL.get());
	}

	private VariantsAndVenturesItems() {
	}
}
