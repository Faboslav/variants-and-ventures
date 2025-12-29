package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Monster.class)
public abstract class ZombieMonsterMixin extends ZombiePathfinderMobMixin
{
	public ZombieMonsterMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}
}
