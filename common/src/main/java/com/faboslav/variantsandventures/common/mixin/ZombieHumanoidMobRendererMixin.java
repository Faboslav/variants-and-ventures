package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HumanoidMobRenderer.class)
public abstract class ZombieHumanoidMobRendererMixin extends ZombieMobRendererMixin
{
	protected ZombieHumanoidMobRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}
}
