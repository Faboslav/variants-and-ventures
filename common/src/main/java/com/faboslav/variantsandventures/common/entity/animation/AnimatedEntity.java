package com.faboslav.variantsandventures.common.entity.animation;

import com.faboslav.variantsandventures.common.client.animation.KeyframeAnimation;
import com.faboslav.variantsandventures.common.client.animation.animator.context.AnimationContextTracker;
import com.faboslav.variantsandventures.common.client.animation.animator.context.KeyframeAnimationContext;

import java.util.ArrayList;

public interface AnimatedEntity
{
	AnimationContextTracker getAnimationContextTracker();

	ArrayList<KeyframeAnimation> getAnimations();

	KeyframeAnimation getMovementAnimation();

	int getKeyframeAnimationTicks();

	void setKeyframeAnimationTicks(int keyframeAnimationTicks);

	default void updateKeyframeAnimationTicks() {
		if (this.isAnyKeyframeAnimationRunning() == false) {
			return;
		}

		this.setKeyframeAnimationTicks(this.getKeyframeAnimationTicks() - 1);

		if (this.getKeyframeAnimationTicks() > 1) {
			return;
		}

		for (KeyframeAnimation keyframeAnimation : this.getAnimations()) {
			if (keyframeAnimation.getAnimation().looping() == false) {
				continue;
			}

			var keyframeAnimationContext = this.getAnimationContextTracker().get(keyframeAnimation);
			if (keyframeAnimationContext.isRunning() == false) {
				continue;
			}

			this.setKeyframeAnimationTicks(keyframeAnimation.getAnimationLengthInTicks());
		}
	}

	default boolean isAnyKeyframeAnimationRunning() {
		return this.getKeyframeAnimationTicks() > 0;
	}

	default boolean isKeyframeAnimationAtLastKeyframe(KeyframeAnimation keyframeAnimation) {
		return this.getAnimationContextTracker().get(keyframeAnimation).isAtLastKeyframe();
	}

	default boolean isKeyframeAnimationRunning(KeyframeAnimation keyframeAnimation) {
		return this.getAnimationContextTracker().get(keyframeAnimation).isRunning();
	}

	default void startKeyframeAnimation(KeyframeAnimation keyframeAnimation, int initialTick) {
		KeyframeAnimationContext keyframeAnimationContext = this.getAnimationContextTracker().get(keyframeAnimation);
		keyframeAnimationContext.setInitialTick(initialTick);
		keyframeAnimationContext.getAnimationState().start(initialTick);
	}

	default void forceStartKeyframeAnimation(KeyframeAnimation keyframeAnimation, int initialTick) {
		KeyframeAnimationContext keyframeAnimationContext = this.getAnimationContextTracker().get(keyframeAnimation);
		keyframeAnimationContext.setInitialTick(initialTick);
		keyframeAnimationContext.getAnimationState().start(initialTick);
	}

	default void stopRunningKeyframeAnimations() {
		for (KeyframeAnimation keyframeAnimation : this.getAnimations()) {
			if (this.getAnimationContextTracker().get(keyframeAnimation).isRunning() == false) {
				this.stopKeyframeAnimation(keyframeAnimation);
			}
		}
	}

	default void stopKeyframeAnimation(KeyframeAnimation keyframeAnimation) {
		KeyframeAnimationContext keyframeAnimationContext = this.getAnimationContextTracker().get(keyframeAnimation);
		keyframeAnimationContext.setInitialTick(0);
		keyframeAnimationContext.setCurrentTick(0);
		keyframeAnimationContext.getAnimationState().stop();
	}
}
