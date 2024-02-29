package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public final class ItemRegistry
{
	@ExpectPlatform
	public static <T extends Item> Supplier<T> register(String name, Supplier<T> item) {
		throw new AssertionError();
	}

	private ItemRegistry() {
	}
}
