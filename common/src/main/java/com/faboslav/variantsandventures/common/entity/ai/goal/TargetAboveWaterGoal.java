package com.faboslav.variantsandventures.common.entity.ai.goal;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

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

	public boolean canUse() {
		//? if >=1.21.5 {
		var isDay = this.murk.level().isBrightOutside();
		//?} else {
		/*var isDay = this.murk.level().isDay();
		*///?}
		return !isDay && this.murk.isInWater() && this.murk.getY() < (double) (this.minY - 2);
	}

	public boolean canContinueToUse() {
		return this.canUse() && !this.foundTarget;
	}

	public void tick() {
		if (this.murk.getY() < (double) (this.minY - 1) && (this.murk.getNavigation().isDone() || this.murk.hasFinishedCurrentPath())) {
			Vec3 vec3d = DefaultRandomPos.getPosTowards(this.murk, 4, 8, new Vec3(this.murk.getX(), this.minY - 1, this.murk.getZ()), 1.5707963705062866);
			if (vec3d == null) {
				this.foundTarget = true;
				return;
			}

			this.murk.getNavigation().moveTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
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