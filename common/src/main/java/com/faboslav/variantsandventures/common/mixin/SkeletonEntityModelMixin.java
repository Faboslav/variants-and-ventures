package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.animation.animator.ModelAnimator;
import com.faboslav.variantsandventures.common.client.model.AnimatedEntityModel;
import com.faboslav.variantsandventures.common.entity.animation.AnimatedEntity;
import com.faboslav.variantsandventures.common.entity.pose.SkeletonEntityPose;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkeletonEntityModel.class)
@Environment(EnvType.CLIENT)
public abstract class SkeletonEntityModelMixin<T extends MobEntity & RangedAttackMob> extends BipedEntityModel<T> implements AnimatedEntityModel
{
	protected ModelPart root;

	public SkeletonEntityModelMixin(ModelPart root) {
		super(root);
		this.root = root;
	}

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void variantsandventures$SkeletonEntityModelMixin(ModelPart root, CallbackInfo ci) {
		this.root = root;
	}

	public ModelPart variantsandventures$getPart() {
		return this.root;
	}

	private void variantsandventures$updateKeyframeAnimations(
		AnimatedEntity animatedEntity,
		float animationProgress
	) {
		animatedEntity.getAnimations().forEach((keyframeAnimation -> {
			ModelAnimator.updateKeyframeAnimations(animatedEntity, this, keyframeAnimation, animationProgress);
		}));
	}

	private void variantsandventures$updateMovementKeyframeAnimations(
		AnimatedEntity animatedEntity,
		float limbAngle,
		float limbDistance,
		float limbAngleScale,
		float limbDistanceScale
	) {
		long l = (long) (limbAngle * 50.0F * limbAngleScale);
		float f = Math.min(limbDistance * limbDistanceScale, 1.0F);
		ModelAnimator.updateMovementKeyframeAnimations(animatedEntity, this, l, f);
	}

	@Inject(
		method = "setAngles",
		at = @At("HEAD"),
		cancellable = true
	)
	public void variantsandventures$setAngles(
		T skeleton,
		float limbAngle,
		float limbDistance,
		float animationProgress,
		float headYaw,
		float headPitch,
		CallbackInfo ci
	) {
		if (VariantsAndVentures.getConfig().enableKeyframeAnimationsForSkeletonAndItsVariants) {
			this.variantsandventures$getPart().traverse().forEach(ModelPart::resetTransform);

			if (skeleton.isInPose(SkeletonEntityPose.EMERGE.get())) {
				this.variantsandventures$updateMovementKeyframeAnimations((AnimatedEntity) skeleton, limbAngle, limbDistance, 1.5F, 2.5F);
				this.variantsandventures$updateKeyframeAnimations((AnimatedEntity) skeleton, animationProgress);
				ci.cancel();
			}
		}
	}
}
