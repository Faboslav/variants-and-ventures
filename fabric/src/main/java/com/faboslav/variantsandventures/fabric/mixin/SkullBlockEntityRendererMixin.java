package com.faboslav.variantsandventures.fabric.mixin;

import com.faboslav.variantsandventures.common.events.client.RegisterSkullModelEvent;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class SkullBlockEntityRendererMixin
{
	private static ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel> variantsandventures$builder = ImmutableMap.builder();

	@Inject(
		at = @At("RETURN"),
		method = "getModels",
		cancellable = true
	)
	private static void variantsandventures$getModels(
		EntityModelLoader modelLoader,
		CallbackInfoReturnable<Map<SkullBlock.SkullType, SkullBlockEntityModel>> cir
	) {
		variantsandventures$builder = ImmutableMap.builder();
		variantsandventures$builder.putAll(cir.getReturnValue());
		RegisterSkullModelEvent.EVENT.invoke(new RegisterSkullModelEvent(SkullBlockEntityRendererMixin::variantsandventures$addModel, modelLoader));
		cir.setReturnValue(variantsandventures$builder.build());
	}

	private static void variantsandventures$addModel(
		SkullBlock.SkullType skullType,
		SkullBlockEntityModel skullBlockEntityModel
	) {
		variantsandventures$builder.put(skullType, skullBlockEntityModel);
	}
}
