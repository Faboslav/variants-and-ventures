package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.Block;

import java.util.function.Supplier;

public final class BlockRegistry
{
	@ExpectPlatform
	public static <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
		throw new AssertionError();
	}

	private BlockRegistry() {
	}
}
