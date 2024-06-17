package com.faboslav.variantsandventures.common.entity.pose;

import net.minecraft.entity.EntityPose;

public enum ZombieEntityPose
{
	IDLE(EntityPose.STANDING),
	EMERGE(EntityPose.EMERGING);

	private final EntityPose originalEntityPose;

	public EntityPose get() {
		return this.originalEntityPose;
	}

	ZombieEntityPose(final EntityPose originalEntityPose) {
		this.originalEntityPose = originalEntityPose;
	}
}