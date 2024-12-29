package com.faboslav.variantsandventures.common.entity.ai.goal;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

public final class LeaveWaterGoal extends MoveToBlockGoal
{
	private final MurkEntity murk;

	public LeaveWaterGoal(MurkEntity murk, double speed) {
		super(murk, speed, 8, 2);
		this.murk = murk;
	}

	public boolean canUse() {
		return super.canUse() && !this.murk.level().isDay() && this.murk.isInWater() && this.murk.getY() >= (double) (this.murk.level().getSeaLevel() - 3);
	}

	public boolean canContinueToUse() {
		return super.canContinueToUse();
	}

	protected boolean isValidTarget(LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		return world.isEmptyBlock(blockPos) && world.isEmptyBlock(blockPos.above()) && world.getBlockState(pos).entityCanStandOn(world, pos, this.murk);
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