package com.faboslav.variantsandventures.common.entity.ai.control;

import com.faboslav.variantsandventures.common.entity.pose.ZombieEntityPose;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.mob.ZombieEntity;

public final class ZombieLookControl extends LookControl
{
	private final ZombieEntity zombieEntity;

	public ZombieLookControl(ZombieEntity zombieEntity) {
		super(zombieEntity);
		this.zombieEntity = zombieEntity;
	}

	@Override
	public void tick() {
		if (zombieEntity.isInPose(ZombieEntityPose.EMERGE.get())) {
			return;
		}

		super.tick();
	}
}
