package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.animation.KeyframeAnimation;
import com.faboslav.variantsandventures.common.client.animation.SkeletonAnimations;
import com.faboslav.variantsandventures.common.client.animation.animator.context.AnimationContextTracker;
import com.faboslav.variantsandventures.common.entity.ai.control.AbstractSkeletonLookControl;
import com.faboslav.variantsandventures.common.entity.ai.control.AbstractSkeletonMoveControl;
import com.faboslav.variantsandventures.common.entity.animation.AnimatedEntity;
import com.faboslav.variantsandventures.common.entity.pose.SkeletonEntityPose;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
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

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonEntityMixin extends SkeletonEntityMobEntityMixin implements AnimatedEntity
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
		return SkeletonAnimations.ANIMATIONS;
	}

	@Override
	public KeyframeAnimation getMovementAnimation() {
		return SkeletonAnimations.WALK;
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

		if (this.variantsandventures$isInPose(SkeletonEntityPose.IDLE) && this.variantsandventures$isMoving() == false) {
			keyframeAnimation = SkeletonAnimations.IDLE;
		} else if (this.variantsandventures$isInPose(SkeletonEntityPose.EMERGE)) {
			keyframeAnimation = SkeletonAnimations.EMERGE;
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
	private void variantsandventures$AbstractSkeletonEntityMixin(
		EntityType<? extends AbstractSkeletonEntity> entityType,
		World world,
		CallbackInfo ci
	) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants) {
			this.moveControl = new AbstractSkeletonMoveControl((AbstractSkeletonEntity) (Object) this);
			this.lookControl = new AbstractSkeletonLookControl((AbstractSkeletonEntity) (Object) this);
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
		CallbackInfoReturnable<EntityData> cir
	) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants
			&& (
				spawnReason == SpawnReason.NATURAL
				|| spawnReason == SpawnReason.SPAWNER
				|| spawnReason == SpawnReason.CHUNK_GENERATION
				|| spawnReason == SpawnReason.STRUCTURE
			)
		) {
			this.variantsandventures$setPose(SkeletonEntityPose.EMERGE);
		}
	}

	@Inject(
		method = "tickMovement",
		at = @At("HEAD")
	)
	public void variantsandventures$tickStart(CallbackInfo ci) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants) {
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
		method = "tickMovement",
		at = @At("TAIL")
	)
	public void variantsandventures$tickEnd(CallbackInfo ci) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants) {
			if (
				this.getWorld().isClient()
				&& this.variantsandventures$isInPose(SkeletonEntityPose.EMERGE)
			) {
				this.variantsandventures$addDigParticles();
			}

			if (
				this.getWorld().isClient() == false
				&& this.isInPose(SkeletonEntityPose.EMERGE.get())
				&& this.getKeyframeAnimationTicks() == 0
			) {
				this.setPose(SkeletonEntityPose.IDLE.get());
			}
		}
	}

	@Override
	public void variantsandventures$skeleton$createSpawnPacket(CallbackInfoReturnable<Packet<?>> cir) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants) {
			cir.setReturnValue(new EntitySpawnS2CPacket((Entity) (Object) this, this.variantsandventures$isInPose(SkeletonEntityPose.EMERGE.get()) ? 1:0));
		}
	}

	@Override
	public void variantsandventures$skeleton$onSpawnPacket(EntitySpawnS2CPacket packet, CallbackInfo ci) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants && packet.getEntityData() == 1) {
			this.setPose(SkeletonEntityPose.EMERGE.get());
		}
	}

	@Override
	public void variantsandventures$skeleton$isInvulnerableTo(
		DamageSource damageSource,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants
			&& cir.getReturnValue() == false
			&& this.variantsandventures$isEmerging()
		) {
			cir.setReturnValue(true);
		}
	}

	@Override
	public void variantsandventures$skeleton$isImmuneToExplosion(CallbackInfoReturnable<Boolean> cir) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants
			&& cir.getReturnValue() == false
			&& this.variantsandventures$isEmerging()
		) {
			cir.setReturnValue(true);
		}
	}

	@Override
	public void variantsandventures$skeleton$isPushable(CallbackInfoReturnable<Boolean> cir) {
		if (
			VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants
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

	public void variantsandventures$setPose(SkeletonEntityPose pose) {
		if (this.getWorld().isClient()) {
			return;
		}

		this.setPose(pose.get());
	}

	public boolean variantsandventures$isInPose(SkeletonEntityPose pose) {
		return this.getPose() == pose.get();
	}


	private boolean variantsandventures$isInPose(EntityPose pose) {
		return this.getPose() == pose;
	}

	private boolean variantsandventures$isEmerging() {
		return this.variantsandventures$isInPose(SkeletonEntityPose.EMERGE.get());
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
