package com.faboslav.variantsandventures.common.platform.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class ItemRegistryImpl
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VariantsAndVentures.MOD_ID);

	public static <T extends Item> Supplier<T> register(String name, Supplier<T> item) {
		return ITEMS.register(name, item);
	}

	private ItemRegistryImpl() {
	}
}
