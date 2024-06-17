package com.faboslav.variantsandventures.common.entity.ai.goal;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public final class LeaveWaterGoal extends MoveToTargetPosGoal
{
	private final MurkEntity murk;

	public LeaveWaterGoal(MurkEntity murk, double speed) {
		super(murk, speed, 8, 2);
		this.murk = murk;
	}

	public boolean canStart() {
		return super.canStart() && !this.murk.getWorld().isDay() && this.murk.isTouchingWater() && this.murk.getY() >= (double) (this.murk.getWorld().getSeaLevel() - 3);
	}

	public boolean shouldContinue() {
		return super.shouldContinue();
	}

	protected boolean isTargetPos(WorldView world, BlockPos pos) {
		BlockPos blockPos = pos.up();
		return world.isAir(blockPos) && world.isAir(blockPos.up()) && world.getBlockState(pos).hasSolidTopSurface(world, pos, this.murk);
	}

	public void start() {
		this.murk.setTargetingUnderwater(false);
		this.murk.setLandNavigation();
		super.start();
	}

	public void stop() {
		super.stop();
	}
}