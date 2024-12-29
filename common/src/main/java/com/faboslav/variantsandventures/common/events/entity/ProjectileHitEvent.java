package com.faboslav.variantsandventures.common.events.entity;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record ProjectileHitEvent(Projectile projectile, EntityHitResult hitResult)
{
	public static final EventHandler<ProjectileHitEvent> EVENT = new EventHandler<>();
}
