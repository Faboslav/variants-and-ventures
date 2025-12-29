package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractZombieRenderer.class)
public abstract class ZombieAbstractZombieRendererMixin extends ZombieHumanoidMobRendererMixin
{
	protected ZombieAbstractZombieRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}
}
