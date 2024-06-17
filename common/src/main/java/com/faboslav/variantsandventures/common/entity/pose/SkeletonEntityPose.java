package com.faboslav.variantsandventures.common.entity.pose;

import net.minecraft.entity.EntityPose;

public enum SkeletonEntityPose
{
	IDLE(EntityPose.STANDING),
	EMERGE(EntityPose.EMERGING);

	private final EntityPose originalEntityPose;

	public EntityPose get() {
		return this.originalEntityPose;
	}

	SkeletonEntityPose(final EntityPose originalEntityPose) {
		this.originalEntityPose = originalEntityPose;
	}
}