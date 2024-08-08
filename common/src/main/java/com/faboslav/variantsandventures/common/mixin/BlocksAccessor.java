package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Blocks.class)
public interface BlocksAccessor
{
	@Invoker("createLogBlock")
	static PillarBlock variantsandventures$createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
		throw new UnsupportedOperationException();
	}

	@Invoker("createLeavesBlock")
	static LeavesBlock variantsandventures$createLeavesBlock(BlockSoundGroup soundGroup) {
		throw new UnsupportedOperationException();
	}
}
