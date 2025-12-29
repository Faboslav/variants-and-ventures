package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
//?}

@Mixin(HumanoidMobRenderer.class)
//? if >= 1.21.3 {
public abstract class ZombieHumanoidMobRendererMixin<T extends Mob, S extends HumanoidRenderState, M extends HumanoidModel<S>> extends ZombieMobRendererMixin<T, S, M>
//?} else {
/*public abstract class ZombieHumanoidMobRendererMixin<T extends Mob, M extends HumanoidModel<T>> extends ZombieMobRendererMixin<T, M>
*///?}
{
	protected ZombieHumanoidMobRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}
}
