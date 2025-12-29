package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.api.ZombieApi;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
//?}

@Mixin(ZombieRenderer.class)
//? if >= 1.21.3 {
public abstract class ZombieRendererMixin<S extends LivingEntityRenderState> extends ZombieAbstractZombieRendererMixin<Zombie, ZombieRenderState, ZombieModel<ZombieRenderState>>
//?} else {
/*public abstract class ZombieRendererMixin extends ZombieAbstractZombieRendererMixin<Zombie, ZombieModel<Zombie>>
*///?}
{
	protected ZombieRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	//? if >= 1.21.3 {
	public boolean variantsandventures$isShaking(ZombieRenderState zombieRenderState, Operation<Boolean> original)
	//?} else {
	/*public boolean variantsandventures$isShaking(LivingEntity entity, Operation<Boolean> original)
	*///?}
	{
		//? if >= 1.21.3 {
		return ((ZombieApi)zombieRenderState).variantsandventures$isShaking();
		//?} else {
		/*return ((ZombieApi)entity).variantsandventures$isShaking();
		*///?}
	}
}
