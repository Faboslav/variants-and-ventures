package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;

//? <=1.21.5 {
/*import org.spongepowered.asm.mixin.gen.Accessor;
*///?}

@Mixin(Projectile.class)
public interface ProjectileEntityAccessor
{
	//? <=1.21.5 {
	/*@Accessor("cachedOwner")
	Entity getOwner();
	*///?}
}
