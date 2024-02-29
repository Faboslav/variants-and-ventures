package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SkullBlock.class)
public interface SkullBlockAccessor
{
	@Invoker("<init>")
	static SkullBlock variantsandventures$createSkullBlock(
		SkullBlock.SkullType skullType,
		AbstractBlock.Settings settings
	) {
		throw new UnsupportedOperationException();
	}
}
