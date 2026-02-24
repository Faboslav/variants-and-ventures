package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.events.entity.ProjectileHitEvent;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Projectile.class)
public abstract class ProjectileEntityMixin extends Entity
{
	public ProjectileEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@WrapMethod(
		method = "onHitEntity"
	)
	private void variantsandventures$onEntityHit(EntityHitResult entityHitResult, Operation<Void> original) {
		ProjectileHitEvent.EVENT.invoke(new ProjectileHitEvent((Projectile) (Object) this, entityHitResult));
		original.call(entityHitResult);
	}
}
