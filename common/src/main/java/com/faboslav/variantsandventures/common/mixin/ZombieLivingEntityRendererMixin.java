package com.faboslav.variantsandventures.common.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntityRenderer.class)
public abstract class ZombieLivingEntityRendererMixin extends EntityRenderer
{
	protected ZombieLivingEntityRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}

	@WrapMethod(
		method = "isShaking"
	)
	public boolean variantsandventures$isShaking(LivingEntity entity, Operation<Boolean> original) {
		return original.call(entity);
	}
}
