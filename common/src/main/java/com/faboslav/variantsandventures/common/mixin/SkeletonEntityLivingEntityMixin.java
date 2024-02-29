package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class SkeletonEntityLivingEntityMixin extends SkeletonEntityEntityMixin
{
	@Shadow
	public abstract Random getRandom();

	@Inject(
		method = "createSpawnPacket",
		at = @At("TAIL"),
		cancellable = true
	)
	public void variantsandventures$skeleton$createSpawnPacket(CallbackInfoReturnable<Packet<?>> cir) {
	}
}
