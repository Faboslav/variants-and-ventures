package com.faboslav.variantsandventures.common.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
//?}

@Mixin(LivingEntityRenderer.class)
//? if >= 1.21.3 {
public abstract class ZombieLivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S>
//?} else {
/*public abstract class ZombieLivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T>
*///?}
{
	protected ZombieLivingEntityRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}

	@WrapMethod(
		method = "isShaking"
	)
	//? if >= 1.21.3 {
	public boolean variantsandventures$isShaking(S entityRenderState, Operation<Boolean> original)
	//?} else {
	/*public boolean variantsandventures$isShaking(LivingEntity entity, Operation<Boolean> original)
	*///?}
	{
		//? if >= 1.21.3 {
		return original.call(entityRenderState);
		//?} else {
		/*return original.call(entity);
		*///?}
	}
}
