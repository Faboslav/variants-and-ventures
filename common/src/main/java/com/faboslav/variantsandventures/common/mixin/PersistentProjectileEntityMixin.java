package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractArrow.class)
public abstract class PersistentProjectileEntityMixin
{
	@ModifyReturnValue(
		method = "getWaterInertia",
		at = @At("RETURN")
	)
	protected float variantsandventures$getWaterInertia(float originalWaterInertia) {
		if (((ProjectileEntityAccessor) this).getOwner() instanceof MurkEntity) {
			return 0.99F;
		}

		return originalWaterInertia;
	}
}
