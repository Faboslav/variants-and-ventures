package com.faboslav.variantsandventures.common.entity.ai.control;

import com.faboslav.variantsandventures.common.entity.pose.SkeletonEntityPose;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.mob.AbstractSkeletonEntity;

public final class AbstractSkeletonLookControl extends LookControl
{
	private final AbstractSkeletonEntity abstractSkeleton;

	public AbstractSkeletonLookControl(AbstractSkeletonEntity abstractSkeleton) {
		super(abstractSkeleton);
		this.abstractSkeleton = abstractSkeleton;
	}

	@Override
	public void tick() {
		if (abstractSkeleton.isInPose(SkeletonEntityPose.EMERGE.get())) {
			return;
		}

		super.tick();
	}
}
