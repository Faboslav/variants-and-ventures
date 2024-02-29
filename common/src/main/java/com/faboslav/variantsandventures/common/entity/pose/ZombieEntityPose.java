package com.faboslav.variantsandventures.common.entity.pose;

import net.minecraft.entity.EntityPose;

public enum ZombieEntityPose
{
	EMERGE,
	IDLE;

	public String getName() {
		return "ZOMBIE_" + this.name();
	}

	public EntityPose get() {
		return EntityPose.valueOf(this.getName());
	}

	static {
		EntityPose.values();
	}
}