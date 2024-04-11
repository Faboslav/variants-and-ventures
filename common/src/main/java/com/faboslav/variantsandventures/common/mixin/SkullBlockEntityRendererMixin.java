package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.client.render.entity.GelidEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.MurkEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.ThicketEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.VerdantEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.model.MurkSkullEntityModel;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class SkullBlockEntityRendererMixin
{
	@Inject(
		at = @At("RETURN"),
		method = "getModels",
		cancellable = true
	)
	private static void variantsandventures$getModels(
		EntityModelLoader modelLoader,
		CallbackInfoReturnable<Map<SkullBlock.SkullType, SkullBlockEntityModel>> cir
	) {
		ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel> builder = ImmutableMap.builder();
		builder.put(SkullBlockType.GELID.get(), new SkullEntityModel(modelLoader.getModelPart(EntityModelLayers.ZOMBIE_HEAD)));
		builder.put(SkullBlockType.MURK.get(), new MurkSkullEntityModel(modelLoader.getModelPart(VariantsAndVenturesModelLayers.MURK_SKULL)));
		builder.put(SkullBlockType.THICKET.get(), new SkullEntityModel(modelLoader.getModelPart(EntityModelLayers.ZOMBIE_HEAD)));
		builder.put(SkullBlockType.VERDANT.get(), new SkullEntityModel(modelLoader.getModelPart(EntityModelLayers.SKELETON_SKULL)));
		builder.putAll(cir.getReturnValue());
		cir.setReturnValue(builder.build());
	}

	@WrapOperation(
		method = "getRenderLayer",
		at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 0)
	)
	private static Object variantsandventures$getIdentifier(Map instance, Object o, Operation<Identifier> original) {
		SkullBlock.SkullType skullType = (SkullBlock.SkullType) o;

		if (skullType == SkullBlockType.GELID.get()) {
			return GelidEntityRenderer.TEXTURE;
		}

		if (skullType == SkullBlockType.MURK.get()) {
			return MurkEntityRenderer.TEXTURES.get(MurkEntity.Variant.PURPLE);
		}

		if (skullType == SkullBlockType.THICKET.get()) {
			return ThicketEntityRenderer.TEXTURE;
		}

		if (skullType == SkullBlockType.VERDANT.get()) {
			return VerdantEntityRenderer.TEXTURE;
		}

		return original.call(instance, o);
	}
}
