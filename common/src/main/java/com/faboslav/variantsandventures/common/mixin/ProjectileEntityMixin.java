package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class ProjectileEntityMixin extends Entity
{
	public ProjectileEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(
		method = "onHitEntity",
		at = @At("HEAD"),
		cancellable = true
	)
	private void variantsandventures$onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
		ProjectileHitEvent.EVENT.invoke(new ProjectileHitEvent((Projectile) (Object) this, entityHitResult));
	}
}
