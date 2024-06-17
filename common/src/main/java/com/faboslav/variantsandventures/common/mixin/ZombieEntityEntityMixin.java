package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ZombieEntityEntityMixin
{
	@Shadow
	public abstract void setPose(EntityPose pose);

	@Shadow
	public abstract EntityPose getPose();

	@Shadow
	public abstract boolean isOnGround();

	@Shadow
	public abstract Vec3d getVelocity();

	@Shadow
	public abstract World getWorld();

	@Shadow
	@Final
	public abstract double getX();

	@Shadow
	@Final
	public abstract double getY();

	@Shadow
	@Final
	public abstract double getZ();

	@Shadow
	public abstract BlockState getSteppingBlockState();

	@Shadow
	public int age;

	@Shadow
	public abstract boolean isInPose(EntityPose pose);

	@Shadow
	public abstract Random getRandom();

	@Inject(
		method = "onSpawnPacket",
		at = @At("TAIL"),
		cancellable = true
	)
	public void variantsandventures$onSpawnPacket(EntitySpawnS2CPacket packet, CallbackInfo ci) {
	}

	@Inject(
		method = "isInvulnerableTo",
		at = @At("TAIL"),
		cancellable = true
	)
	public void variantsandventures$isInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
	}

	@Inject(
		method = "isImmuneToExplosion",
		at = @At("TAIL"),
		cancellable = true
	)
	public void isImmuneToExplosion(CallbackInfoReturnable<Boolean> cir) {
	}

	@Inject(
		method = "isPushable",
		at = @At("TAIL"),
		cancellable = true
	)
	public void isPushable(CallbackInfoReturnable<Boolean> cir) {
	}

	@Inject(
		method = "createSpawnPacket",
		at = @At("TAIL"),
		cancellable = true
	)
	public void variantsandventures$createSpawnPacket(
		EntityTrackerEntry entityTrackerEntry,
		CallbackInfoReturnable<Packet<?>> cir
	) {
	}
}
