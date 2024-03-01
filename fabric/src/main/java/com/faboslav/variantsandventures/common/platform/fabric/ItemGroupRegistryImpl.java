package com.faboslav.variantsandventures.common.platform.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public final class ItemGroupRegistryImpl
{
	public static void addToItemGroupBefore(ItemGroup itemGroup, Item item, Item before) {
		ItemGroupEvents.modifyEntriesEvent(itemGroup).register((content) -> {
			content.addBefore(before, item.getDefaultStack());
		});
	}

	public static void addToItemGroupAfter(ItemGroup itemGroup, Item item, Item after) {
		ItemGroupEvents.modifyEntriesEvent(itemGroup).register((content) -> {
			content.addAfter(after, item.getDefaultStack());
		});
	}

	private ItemGroupRegistryImpl() {
	}
}
