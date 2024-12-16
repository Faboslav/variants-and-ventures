package com.faboslav.variantsandventures.common.entity.ai;

import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.SnowballItem;

import java.util.EnumSet;

public final class GelidSnowballRangedAttackGoal extends Goal
{
	private final GelidEntity gelid;
	private LivingEntity target;
	private int seenTargetTicks;
	private final float maxShootRange;
	private final float squaredMaxShootRange;

	public GelidSnowballRangedAttackGoal(GelidEntity gelid, float maxShootRange) {
		this.gelid = gelid;
		this.maxShootRange = maxShootRange;
		this.squaredMaxShootRange = maxShootRange * maxShootRange;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		LivingEntity livingEntity = this.gelid.getTarget();

		if (
			livingEntity == null
			|| livingEntity.isAlive() == false
			|| this.gelid.getOffhandItem().getItem() instanceof SnowballItem == false
		) {
			return false;
		}

		this.target = livingEntity;
		return true;
	}

	public boolean canContinueToUse() {
		return this.canUse() || !this.gelid.getNavigation().isDone();
	}

	public void stop() {
		this.target = null;
		this.seenTargetTicks = 0;
	}

	public boolean requiresUpdateEveryTick() {
		return true;
	}

	public void tick() {
		double d = this.gelid.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
		boolean bl = this.gelid.getSensing().hasLineOfSight(this.target);
		if (bl) {
			++this.seenTargetTicks;
		} else {
			this.seenTargetTicks = 0;
		}

		if (!(d > (double) this.squaredMaxShootRange) && this.seenTargetTicks >= 5) {
			this.gelid.getNavigation().stop();
		} else {
			this.gelid.getNavigation().moveTo(this.target, 1.0F);
		}

		this.gelid.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

		if (!bl) {
			return;
		}

		float f = (float) Math.sqrt(d) / this.maxShootRange;
		float g = Mth.clamp(f, 0.1F, 1.0F);
		this.gelid.throwSnowball(this.target, g);
	}
}
