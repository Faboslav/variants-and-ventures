package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Projectile.class)
public abstract class AbstractArrowProjectileMixin
{
	@Shadow
	public abstract Entity getOwner();
}
