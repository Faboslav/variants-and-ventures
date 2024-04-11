package com.faboslav.variantsandventures.common.events.entity;

import com.faboslav.variantsandventures.common.events.base.CancellableEventHandler;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.WorldAccess;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record EntitySpawnEvent(MobEntity entity, WorldAccess worldAccess, boolean isBaby, SpawnReason spawnReason)
{
	public static final CancellableEventHandler<EntitySpawnEvent> EVENT = new CancellableEventHandler<>();
}
