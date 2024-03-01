package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public final class ItemGroupRegistry
{
	@ExpectPlatform
	public static void addToItemGroupBefore(ItemGroup itemGroup, Item item, Item before) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void addToItemGroupAfter(ItemGroup itemGroup, Item item, Item after) {
		throw new AssertionError();
	}

	private ItemGroupRegistry() {
	}
}
