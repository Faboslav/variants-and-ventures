package com.faboslav.variantsandventures.common.entity.ai.goal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public final class WanderAroundOnSurfaceGoal extends Goal
{
	private final PathAwareEntity mob;
	private double x;
	private double y;
	private double z;
	private final double speed;
	private final World world;

	public WanderAroundOnSurfaceGoal(PathAwareEntity mob, double speed) {
		this.mob = mob;
		this.speed = speed;
		this.world = mob.getWorld();
		this.setControls(EnumSet.of(Control.MOVE));
	}

	public boolean canStart() {
		if (!this.world.isDay()) {
			return false;
		} else if (this.mob.isTouchingWater()) {
			return false;
		} else {
			Vec3d vec3d = this.getWanderTarget();
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

	public boolean shouldContinue() {
		return !this.mob.getNavigation().isIdle();
	}

	public void start() {
		this.mob.getNavigation().startMovingTo(this.x, this.y, this.z, this.speed);
	}

	@Nullable
	private Vec3d getWanderTarget() {
		Random random = this.mob.getRandom();
		BlockPos blockPos = this.mob.getBlockPos();

		for (int i = 0; i < 10; ++i) {
			BlockPos blockPos2 = blockPos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
			if (this.world.getBlockState(blockPos2).isOf(Blocks.WATER)) {
				return Vec3d.ofBottomCenter(blockPos2);
			}
		}

		return null;
	}
}
