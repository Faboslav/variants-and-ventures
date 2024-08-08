package com.faboslav.variantsandventures.common.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TrunkPlacerType.class)
public interface TrunkPlacerTypeAccessor
{
	@Invoker("<init>")
	static TrunkPlacerType variantsandventures$createTrunkPlacerType(Codec<?> codec) {
		throw new UnsupportedOperationException();
	}
}
