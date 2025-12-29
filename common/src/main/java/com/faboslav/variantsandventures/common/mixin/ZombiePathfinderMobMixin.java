package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PathfinderMob.class)
public abstract class ZombiePathfinderMobMixin extends ZombieMobMixin
{
	public ZombiePathfinderMobMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}
}
