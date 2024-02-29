package com.faboslav.variantsandventures.common.client.animation.animator.context;

import com.faboslav.variantsandventures.common.entity.animation.AnimationState;

public final class KeyframeAnimationContext
{
	private AnimationState animationState;
	private final int totalTicks;
	private int initialTick;
	private int currentTick;

	public KeyframeAnimationContext(
		int totalTicks
	) {
		this.totalTicks = totalTicks;
		this.initialTick = 0;
		this.currentTick = 0;
		this.animationState = new AnimationState();
	}

	public void setInitialTick(int initialTick) {
		this.initialTick = initialTick;
	}

	public void setCurrentTick(int currentTick) {
		this.currentTick = currentTick;
	}

	public boolean isRunning() {
		return this.initialTick != 0;
	}

	public boolean isAtLastKeyframe() {
		return initialTick + totalTicks > currentTick;
	}

	public void setAnimationState(AnimationState animationState) {
		this.animationState = animationState;
	}

	public AnimationState getAnimationState() {
		return this.animationState;
	}
}
