package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WallSkullBlock.class)
public interface WallSkullBlockAccessor
{
	@Invoker("<init>")
	static WallSkullBlock variantsandventures$createWallSkullBlock(
		SkullBlock.SkullType skullType,
		AbstractBlock.Settings settings
	) {
		throw new UnsupportedOperationException();
	}
}
