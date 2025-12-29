package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MobRenderer.class)
public abstract class ZombieMobRendererMixin extends ZombieLivingEntityRendererMixin
{
	protected ZombieMobRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}
}
