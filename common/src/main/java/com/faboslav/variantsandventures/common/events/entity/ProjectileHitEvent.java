package com.faboslav.variantsandventures.common.events.entity;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record ProjectileHitEvent(ProjectileEntity projectile, EntityHitResult hitResult)
{
	public static final EventHandler<ProjectileHitEvent> EVENT = new EventHandler<>();
}
