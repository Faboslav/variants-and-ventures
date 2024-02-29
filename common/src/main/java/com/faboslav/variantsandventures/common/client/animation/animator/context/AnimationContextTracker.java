package com.faboslav.variantsandventures.common.client.animation.animator.context;

import com.faboslav.variantsandventures.common.client.animation.KeyframeAnimation;

import java.util.HashMap;
import java.util.Map;

public final class AnimationContextTracker
{
	private final Map<String, KeyframeAnimationContext> animationKeyframeContext = new HashMap<>();

	public KeyframeAnimationContext get(KeyframeAnimation keyframeAnimation) {
		KeyframeAnimationContext keyframeAnimationContext = this.animationKeyframeContext.get(keyframeAnimation.getName());

		if (keyframeAnimationContext == null) {
			throw new RuntimeException(String.format("Keyframe animation '%s' is not added.", keyframeAnimation.getName()));
		}

		return keyframeAnimationContext;
	}

	public void add(KeyframeAnimation keyframeAnimation) {
		this.animationKeyframeContext.put(keyframeAnimation.getName(), new KeyframeAnimationContext(
			keyframeAnimation.getAnimationLengthInTicks()
		));
	}
}
