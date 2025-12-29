package com.faboslav.variantsandventures.common.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class ZombieLivingEntityMixin extends Entity
{
	public ZombieLivingEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@WrapMethod(
		method = "canFreeze"
	)
	public boolean variantsandventures$canFreeze(Operation<Boolean> original) {
		return original.call();
	}
}
