package com.faboslav.variantsandventures.common.events.entity;

import com.faboslav.variantsandventures.common.events.base.CancellableEventHandler;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record EntitySpawnEvent(Mob entity, LevelAccessor worldAccess, boolean isBaby, MobSpawnType spawnReason)
{
	public static final CancellableEventHandler<EntitySpawnEvent> EVENT = new CancellableEventHandler<>();
}
