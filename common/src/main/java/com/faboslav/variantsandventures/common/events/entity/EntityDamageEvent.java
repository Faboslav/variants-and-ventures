package com.faboslav.variantsandventures.common.events.entity;

import com.faboslav.variantsandventures.common.events.base.CancellableEventHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record EntityDamageEvent(LivingEntity entity, DamageSource source, float amount)
{
	public static final CancellableEventHandler<EntityDamageEvent> EVENT = new CancellableEventHandler<>();
}
