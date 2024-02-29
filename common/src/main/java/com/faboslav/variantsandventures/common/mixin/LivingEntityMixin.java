package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.events.entity.EntityDamageEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 1200)
public abstract class LivingEntityMixin
{
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	private void variantsandventures$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (EntityDamageEvent.EVENT.invoke(new EntityDamageEvent((LivingEntity) ((Object) this), source, amount))) {
			cir.setReturnValue(false);
		}
	}
}