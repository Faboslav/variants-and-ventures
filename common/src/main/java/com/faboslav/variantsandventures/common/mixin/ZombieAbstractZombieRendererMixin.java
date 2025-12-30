package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
//?}

@Mixin(AbstractZombieRenderer.class)
//? if >= 1.21.3 {
public abstract class ZombieAbstractZombieRendererMixin<T extends Zombie, S extends ZombieRenderState, M extends ZombieModel<S>> extends ZombieHumanoidMobRendererMixin<T, S, M>
//?} else {
/*public abstract class ZombieAbstractZombieRendererMixin<T extends Zombie, M extends ZombieModel<T>> extends ZombieHumanoidMobRendererMixin<T, M>
*///?}
{
	protected ZombieAbstractZombieRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}
}
