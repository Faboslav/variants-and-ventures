package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;

//? if <=1.21.5 {
/*import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.world.entity.Entity;
*///?}

@Mixin(Projectile.class)
public interface ProjectileEntityAccessor
{
	//? if <=1.21.5 {
	/*@Accessor("cachedOwner")
	Entity getOwner();
	*///?}
}
