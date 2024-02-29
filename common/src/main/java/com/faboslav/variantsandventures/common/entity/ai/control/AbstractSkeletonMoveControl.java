package com.faboslav.variantsandventures.common.entity.ai.control;

import com.faboslav.variantsandventures.common.entity.pose.SkeletonEntityPose;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.AbstractSkeletonEntity;

public final class AbstractSkeletonMoveControl extends MoveControl
{
	private final AbstractSkeletonEntity abstractSkeleton;

	public AbstractSkeletonMoveControl(AbstractSkeletonEntity abstractSkeleton) {
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
