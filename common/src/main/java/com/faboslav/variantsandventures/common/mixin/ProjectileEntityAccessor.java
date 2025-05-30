package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Projectile.class)
public interface ProjectileEntityAccessor
{
	@Accessor("cachedOwner")
	Entity getOwner();
}
