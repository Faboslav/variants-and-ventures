package com.faboslav.variantsandventures.common.client.animation.animator;

import com.faboslav.variantsandventures.common.client.animation.KeyframeAnimation;
import com.faboslav.variantsandventures.common.client.animation.animator.context.AnimationContextTracker;
import com.faboslav.variantsandventures.common.client.animation.animator.context.KeyframeAnimationContext;
import com.faboslav.variantsandventures.common.client.model.AnimatedEntityModel;
import com.faboslav.variantsandventures.common.entity.animation.*;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ModelAnimator
{
	private static final Vector3f TEMP = new Vector3f();

	public static void updateKeyframeAnimations(
		AnimatedEntity animatedEntity,
		AnimatedEntityModel animatedEntityModel,
		KeyframeAnimation keyframeAnimation,
		float animationProgress
	) {
		AnimationContextTracker animationContextTracker = animatedEntity.getAnimationContextTracker();
		Animation animation = keyframeAnimation.getAnimation();
		int currentTick = ((Entity) animatedEntity).age;

		KeyframeAnimationContext keyframeAnimationContext = animationContextTracker.get(keyframeAnimation);
		keyframeAnimationContext.setCurrentTick(currentTick);
		AnimationState animationState = keyframeAnimationContext.getAnimationState();

		animationState.update(animationProgress, 1.0F);
		animationState.run((state) -> {
			animateKeyframe(animatedEntityModel, animation, state.getTimeRunning(), 1.0F, TEMP);
		});
	}

	public static void updateMovementKeyframeAnimations(
		AnimatedEntity animatedEntity,
		AnimatedEntityModel animatedEntityModel,
		long runningTime,
		float f
	) {
		KeyframeAnimation keyframeAnimation = animatedEntity.getMovementAnimation();
		Animation animation = keyframeAnimation.getAnimation();
		animateKeyframe(animatedEntityModel, animation, runningTime, f, TEMP);
	}

	public static void animateKeyframe(
		AnimatedEntityModel model,
		Animation animation,
		long runningTime,
		float scale,
		Vector3f vec3f
	) {
		float f = getRunningSeconds(animation, runningTime);
		Iterator var7 = animation.boneAnimations().entrySet().iterator();

		while (var7.hasNext()) {
			Map.Entry<String, List<Transformation>> entry = (Map.Entry) var7.next();
			Optional<ModelPart> optional = model.variantsandventures$getChild(entry.getKey());
			List<Transformation> list = entry.getValue();
			optional.ifPresent((part) -> {
				list.forEach((transformation) -> {
					Keyframe[] keyframes = transformation.keyframes();
					int i = Math.max(0, MathHelper.binarySearch(0, keyframes.length, (index) -> {
						return f <= keyframes[index].timestamp();
					}) - 1);
					int j = Math.min(keyframes.length - 1, i + 1);
					Keyframe keyframe = keyframes[i];
					Keyframe keyframe2 = keyframes[j];
					float h = f - keyframe.timestamp();
					float k;
					if (j != i) {
						k = MathHelper.clamp(h / (keyframe2.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
					} else {
						k = 0.0F;
					}

					keyframe2.interpolation().apply(vec3f, k, keyframes, i, j, scale);

					Transformation.Type type = transformation.type();
					if (type == Transformation.Type.TRANSLATE) {
						part.translate(vec3f);
					} else if (type == Transformation.Type.ROTATE) {
						part.rotate(vec3f);
					} else if (type == Transformation.Type.SCALE) {
						part.scale(vec3f);
					}
				});
			});
		}

	}

	private static float getRunningSeconds(Animation animation, long runningTime) {
		float f = (float) runningTime / 1000.0F;
		return animation.looping() ? f % animation.lengthInSeconds():f;
	}
}
