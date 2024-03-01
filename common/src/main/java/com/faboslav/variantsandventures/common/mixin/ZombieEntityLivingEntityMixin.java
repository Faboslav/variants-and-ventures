package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class ZombieEntityLivingEntityMixin extends ZombieEntityEntityMixin
{
	@Shadow
	public abstract Random getRandom();
}
