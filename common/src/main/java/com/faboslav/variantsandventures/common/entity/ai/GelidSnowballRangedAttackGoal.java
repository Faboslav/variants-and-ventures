package com.faboslav.variantsandventures.common.entity.ai;

import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.SnowballItem;
import net.minecraft.util.math.MathHelper;

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
		this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
	}

	public boolean canStart() {
		LivingEntity livingEntity = this.gelid.getTarget();

		if (
			livingEntity == null
			|| livingEntity.isAlive() == false
			|| this.gelid.getOffHandStack().getItem() instanceof SnowballItem == false
		) {
			return false;
		}

		this.target = livingEntity;
		return true;
	}

	public boolean shouldContinue() {
		return this.canStart() || !this.gelid.getNavigation().isIdle();
	}

	public void stop() {
		this.target = null;
		this.seenTargetTicks = 0;
	}

	public boolean shouldRunEveryTick() {
		return true;
	}

	public void tick() {
		double d = this.gelid.squaredDistanceTo(this.target.getX(), this.target.getY(), this.target.getZ());
		boolean bl = this.gelid.getVisibilityCache().canSee(this.target);
		if (bl) {
			++this.seenTargetTicks;
		} else {
			this.seenTargetTicks = 0;
		}

		if (!(d > (double) this.squaredMaxShootRange) && this.seenTargetTicks >= 5) {
			this.gelid.getNavigation().stop();
		} else {
			this.gelid.getNavigation().startMovingTo(this.target, 1.0F);
		}

		this.gelid.getLookControl().lookAt(this.target, 30.0F, 30.0F);

		if (!bl) {
			return;
		}

		float f = (float) Math.sqrt(d) / this.maxShootRange;
		float g = MathHelper.clamp(f, 0.1F, 1.0F);
		this.gelid.throwSnowball(this.target, g);
	}
}
