package com.faboslav.variantsandventures.common.entity.pose;

import net.minecraft.entity.EntityPose;

public enum SkeletonEntityPose
{
	IDLE(EntityPose.STANDING),
	EMERGE(EntityPose.EMERGING);

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return "SKELETON_" + this.name();
	}

	private final EntityPose originalEntityPose;

	public EntityPose get() {
		return this.originalEntityPose;
	}

	SkeletonEntityPose(final EntityPose originalEntityPose) {
		this.originalEntityPose = originalEntityPose;
	}
}