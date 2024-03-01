package com.faboslav.variantsandventures.common.platform.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Supplier;

public final class BlockRegistryImpl
{
	public static <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
		var registry = Registry.register(Registries.BLOCK, VariantsAndVentures.makeID(name), block.get());
		return () -> registry;
	}

	private BlockRegistryImpl() {
	}
}
