package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.animation.KeyframeAnimation;
import com.faboslav.variantsandventures.common.client.animation.ZombieAnimations;
import com.faboslav.variantsandventures.common.client.animation.animator.context.AnimationContextTracker;
import com.faboslav.variantsandventures.common.entity.ai.control.ZombieLookControl;
import com.faboslav.variantsandventures.common.entity.ai.control.ZombieMoveControl;
import com.faboslav.variantsandventures.common.entity.animation.AnimatedEntity;
import com.faboslav.variantsandventures.common.entity.pose.ZombieEntityPose;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends ZombieEntityMobEntityMixin implements AnimatedEntity
{
	private AnimationContextTracker animationContextTracker;
	private int poseTicks = 0;

	@Override
	public AnimationContextTracker getAnimationContextTracker() {
		if (this.animationContextTracker == null) {
			this.animationContextTracker = new AnimationContextTracker();

			for (KeyframeAnimation keyframeAnimation : this.getAnimations()) {
				this.animationContextTracker.add(keyframeAnimation);
			}

			this.animationContextTracker.add(this.getMovementAnimation());
		}

		return this.animationContextTracker;
	}

	@Override
	public ArrayList<KeyframeAnimation> getAnimations() {
		return ZombieAnimations.ANIMATIONS;
	}

	@Override
	public KeyframeAnimation getMovementAnimation() {
		return ZombieAnimations.WALK;
	}

	@Override
	public int getKeyframeAnimationTicks() {
		return this.poseTicks;
	}

	public void setKeyframeAnimationTicks(int keyframeAnimationTicks) {
		this.poseTicks = keyframeAnimationTicks;
	}

	private KeyframeAnimation getKeyframeAnimationByPose() {
		KeyframeAnimation keyframeAnimation = null;

		if (this.variantsandventures$isInPose(ZombieEntityPose.IDLE) && this.variantsandventures$isMoving() == false) {
			keyframeAnimation = ZombieAnimations.IDLE;
		} else if (this.variantsandventures$isInPose(ZombieEntityPose.EMERGE)) {
			keyframeAnimation = ZombieAnimations.EMERGE;
		}

		return keyframeAnimation;
	}

	private void tryToStartKeyframeAnimation(KeyframeAnimation keyframeAnimationToStart) {
		if (this.isKeyframeAnimationRunning(keyframeAnimationToStart)) {
			return;
		}

		if (this.getWorld().isClient() == false) {
			this.setKeyframeAnimationTicks(keyframeAnimationToStart.getAnimationLengthInTicks());
		}

		this.startKeyframeAnimation(keyframeAnimationToStart);
	}

	private void startKeyframeAnimation(KeyframeAnimation keyframeAnimationToStart) {
		for (KeyframeAnimation keyframeAnimation : this.getAnimations()) {
			if (keyframeAnimation == keyframeAnimationToStart) {
				continue;
			}

			this.stopKeyframeAnimation(keyframeAnimation);
		}

		this.startKeyframeAnimation(keyframeAnimationToStart, this.age);
	}

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void variantsandventures$ZombieEntityMixin(
		EntityType<? extends ZombieEntity> entityType,
		World world,
		CallbackInfo ci
	) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants) {
			this.moveControl = new ZombieMoveControl((ZombieEntity) (Object) this);
			this.lookControl = new ZombieLookControl((ZombieEntity) (Object) this);
		}
	}

	@Inject(
		method = "initialize",
		at = @At("HEAD")
	)
	public void initialize(
		ServerWorldAccess world,
		LocalDifficulty difficulty,
		SpawnReason spawnReason,
		EntityData entityData,
		NbtCompound entityNbt,
		CallbackInfoReturnable<EntityData> cir
	) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants
			&& (
				spawnReason == SpawnReason.NATURAL
				|| spawnReason == SpawnReason.SPAWNER
				|| spawnReason == SpawnReason.CHUNK_GENERATION
				|| spawnReason == SpawnReason.STRUCTURE
			)
		) {
			this.variantsandventures$setPose(ZombieEntityPose.EMERGE);
		}
	}

	@Inject(
		method = "tick",
		at = @At("HEAD")
	)
	public void variantsandventures$tickStart(CallbackInfo ci) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants) {
			if (this.getWorld().isClient() == false) {
				this.updateKeyframeAnimationTicks();
			}

			KeyframeAnimation keyframeAnimationToStart = this.getKeyframeAnimationByPose();

			if (keyframeAnimationToStart != null) {
				this.tryToStartKeyframeAnimation(keyframeAnimationToStart);
			}
		}
	}

	@Inject(
		method = "tick",
		at = @At("TAIL")
	)
	public void variantsandventures$tickEnd(CallbackInfo ci) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants) {
			if (
				this.getWorld().isClient()
				&& this.variantsandventures$isInPose(ZombieEntityPose.EMERGE)
			) {
				this.variantsandventures$addDigParticles();
			}

			if (
				this.getWorld().isClient() == false
				&& this.isInPose(ZombieEntityPose.EMERGE.get())
				&& this.getKeyframeAnimationTicks() == 0
			) {
				this.setPose(ZombieEntityPose.IDLE.get());
			}
		}
	}

	@Override
	public void variantsandventures$createSpawnPacket(CallbackInfoReturnable<Packet<?>> cir) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants) {
			cir.setReturnValue(new EntitySpawnS2CPacket((Entity) (Object) this, this.variantsandventures$isInPose(ZombieEntityPose.EMERGE.get()) ? 1:0));
		}
	}

	@Override
	public void variantsandventures$onSpawnPacket(EntitySpawnS2CPacket packet, CallbackInfo ci) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants && packet.getEntityData() == 1) {
			this.setPose(ZombieEntityPose.EMERGE.get());
		}
	}

	@Override
	public void variantsandventures$isInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants
			&& cir.getReturnValue() == false
			&& this.variantsandventures$isEmerging()
		) {
			cir.setReturnValue(true);
		}
	}

	@Override
	public void isImmuneToExplosion(CallbackInfoReturnable<Boolean> cir) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants
			&& cir.getReturnValue() == false
			&& this.variantsandventures$isEmerging()
		) {
			cir.setReturnValue(true);
		}
	}

	@Override
	public void isPushable(CallbackInfoReturnable<Boolean> cir) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForZombieAndItsVariants
			&& cir.getReturnValue() == false
			&& this.variantsandventures$isEmerging()
		) {
			cir.setReturnValue(true);
		}
	}

	public void variantsandventures$setPose(EntityPose pose) {
		if (this.getWorld().isClient()) {
			return;
		}

		this.setPose(pose);
	}

	public void variantsandventures$setPose(ZombieEntityPose pose) {
		if (this.getWorld().isClient()) {
			return;
		}

		this.setPose(pose.get());
	}

	public boolean variantsandventures$isInPose(ZombieEntityPose pose) {
		return this.getPose() == pose.get();
	}


	private boolean variantsandventures$isInPose(EntityPose pose) {
		return this.getPose() == pose;
	}

	private boolean variantsandventures$isEmerging() {
		return this.variantsandventures$isInPose(ZombieEntityPose.EMERGE.get());
	}

	private boolean variantsandventures$isMoving() {
		return this.isOnGround() && this.getVelocity().lengthSquared() >= 0.0001;
	}

	private void variantsandventures$addDigParticles() {
		Random random = this.getRandom();
		BlockState blockState = this.getSteppingBlockState();
		if (blockState.getRenderType() != BlockRenderType.INVISIBLE) {
			for (int i = 0; i < 15; ++i) {
				double d = this.getX() + (double) MathHelper.nextBetween(random, -0.5F, 0.5F);
				double e = this.getY();
				double f = this.getZ() + (double) MathHelper.nextBetween(random, -0.5F, 0.5F);
				this.getWorld().addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), d, e, f, 0.0, 0.0, 0.0);
			}
		}
	}
}
