package com.faboslav.variantsandventures.common.platform.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class BlockRegistryImpl
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VariantsAndVentures.MOD_ID);

	public static <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
		return BLOCKS.register(name, block);
	}

	private BlockRegistryImpl() {
	}
}
