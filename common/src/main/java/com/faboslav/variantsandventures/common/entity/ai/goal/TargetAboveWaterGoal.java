package com.faboslav.variantsandventures.common.entity.ai.goal;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class TargetAboveWaterGoal extends Goal
{
	private final MurkEntity murk;
	private final double speed;
	private final int minY;
	private boolean foundTarget;

	public TargetAboveWaterGoal(MurkEntity murk, double speed, int minY) {
		this.murk = murk;
		this.speed = speed;
		this.minY = minY;
	}

	public boolean canStart() {
		return !this.murk.getWorld().isDay() && this.murk.isTouchingWater() && this.murk.getY() < (double) (this.minY - 2);
	}

	public boolean shouldContinue() {
		return this.canStart() && !this.foundTarget;
	}

	public void tick() {
		if (this.murk.getY() < (double) (this.minY - 1) && (this.murk.getNavigation().isIdle() || this.murk.hasFinishedCurrentPath())) {
			Vec3d vec3d = NoPenaltyTargeting.findTo(this.murk, 4, 8, new Vec3d(this.murk.getX(), this.minY - 1, this.murk.getZ()), 1.5707963705062866);
			if (vec3d == null) {
				this.foundTarget = true;
				return;
			}

			this.murk.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
		}

	}

	public void start() {
		this.murk.setTargetingUnderwater(true);
		this.foundTarget = false;
	}

	public void stop() {
		this.murk.setTargetingUnderwater(false);
	}
}