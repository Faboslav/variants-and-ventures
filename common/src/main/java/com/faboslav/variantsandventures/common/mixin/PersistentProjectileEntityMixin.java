package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractArrow.class)
public abstract class PersistentProjectileEntityMixin
{
	@Inject(
		method = "getWaterInertia",
		at = @At("HEAD"),
		cancellable = true
	)
	public void variantsandventures$getWaterInertia(
		CallbackInfoReturnable<Float> cir
	) {
		if (((ProjectileEntityAccessor) this).getOwner() instanceof MurkEntity) {
			cir.setReturnValue(0.99f);
		}
	}
}
