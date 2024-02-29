package com.faboslav.variantsandventures.common.entity.pose;

import net.minecraft.entity.EntityPose;

public enum SkeletonEntityPose
{
	EMERGE,
	IDLE;

	public String getName() {
		return "SKELETON_" + this.name();
	}

	public EntityPose get() {
		return EntityPose.valueOf(this.getName());
	}

	static {
		EntityPose.values();
	}
}