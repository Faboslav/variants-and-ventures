package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin
{
	@Inject(
		method = "getDragInWater",
		at = @At("HEAD"),
		cancellable = true
	)
	public void variantsandventures$getDragInWater(
		CallbackInfoReturnable<Float> cir
	) {
		if (((ProjectileEntityAccessor) this).getOwner() instanceof MurkEntity) {
			cir.setReturnValue(0.99f);
		}
	}
}
