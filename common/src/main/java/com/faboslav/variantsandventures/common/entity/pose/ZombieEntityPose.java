package com.faboslav.variantsandventures.common.entity.pose;

import net.minecraft.entity.EntityPose;

public enum ZombieEntityPose
{
	EMERGE,
	IDLE;

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

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