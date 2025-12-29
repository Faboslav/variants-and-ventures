package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
 //?}

@Mixin(MobRenderer.class)
//? if >= 1.21.3 {
public abstract class ZombieMobRendererMixin<T extends Mob, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends ZombieLivingEntityRendererMixin<T, S, M>
//?} else {
/*public abstract class ZombieMobRendererMixin<T extends Mob, M extends EntityModel<T>> extends ZombieLivingEntityRendererMixin<T, M>
*///?}
{
	protected ZombieMobRendererMixin(EntityRendererProvider.Context context) {
		super(context);
	}
}
