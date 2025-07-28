package com.faboslav.variantsandventures.common.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public final class WanderAroundOnSurfaceGoal extends Goal
{
	private final PathfinderMob mob;
	private double x;
	private double y;
	private double z;
	private final double speed;
	private final Level world;

	public WanderAroundOnSurfaceGoal(PathfinderMob mob, double speed) {
		this.mob = mob;
		this.speed = speed;
		this.world = mob.level();
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	public boolean canUse() {
		//? if >=1.21.5 {
		var isDay = this.world.isBrightOutside();
		//?} else {
		/*var isDay = this.world.isDay();
		*///?}

		if (!isDay) {
			return false;
		} else if (this.mob.isInWater()) {
			return false;
		} else {
			Vec3 vec3d = this.getWanderTarget();
			if (vec3d == null) {
				return false;
			} else {
				this.x = vec3d.x;
				this.y = vec3d.y;
				this.z = vec3d.z;
				return true;
			}
		}
	}

	public boolean canContinueToUse() {
		return !this.mob.getNavigation().isDone();
	}

	public void start() {
		this.mob.getNavigation().moveTo(this.x, this.y, this.z, this.speed);
	}

	@Nullable
	private Vec3 getWanderTarget() {
		RandomSource random = this.mob.getRandom();
		BlockPos blockPos = this.mob.blockPosition();

		for (int i = 0; i < 10; ++i) {
			BlockPos blockPos2 = blockPos.offset(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
			if (this.world.getBlockState(blockPos2).is(Blocks.WATER)) {
				return Vec3.atBottomCenterOf(blockPos2);
			}
		}

		return null;
	}
}
