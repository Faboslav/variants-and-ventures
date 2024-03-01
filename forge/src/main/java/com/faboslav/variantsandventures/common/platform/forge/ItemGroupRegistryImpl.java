package com.faboslav.variantsandventures.common.platform.forge;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.HashMap;

public final class ItemGroupRegistryImpl
{
	public static final HashMap<ItemGroup, HashMap<Item, Item>> ITEMS_TO_ADD_BEFORE = new HashMap<>();
	public static final HashMap<ItemGroup, HashMap<Item, Item>> ITEMS_TO_ADD_AFTER = new HashMap<>();

	public static void addToItemGroupBefore(ItemGroup itemGroup, Item item, Item before) {
		if (ITEMS_TO_ADD_BEFORE.containsKey(itemGroup)) {
			ITEMS_TO_ADD_BEFORE.get(itemGroup).put(item, before);
		} else {
			HashMap<Item, Item> items = new HashMap<>();
			items.put(item, before);
			ITEMS_TO_ADD_BEFORE.put(itemGroup, items);
		}
	}

	public static void addToItemGroupAfter(ItemGroup itemGroup, Item item, Item after) {
		if (ITEMS_TO_ADD_AFTER.containsKey(itemGroup)) {
			ITEMS_TO_ADD_AFTER.get(itemGroup).put(item, after);
		} else {
			HashMap<Item, Item> items = new HashMap<>();
			items.put(item, after);
			ITEMS_TO_ADD_AFTER.put(itemGroup, items);
		}
	}

	private ItemGroupRegistryImpl() {
	}
}
