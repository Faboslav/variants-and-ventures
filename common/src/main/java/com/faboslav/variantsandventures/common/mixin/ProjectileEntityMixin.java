package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin extends Entity
{
	public ProjectileEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
	private void variantsandventures$onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
		ProjectileHitEvent.EVENT.invoke(new ProjectileHitEvent((ProjectileEntity) (Object) this, entityHitResult));
	}
}
