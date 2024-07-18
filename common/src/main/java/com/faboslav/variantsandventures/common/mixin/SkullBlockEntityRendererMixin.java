package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.client.render.entity.GelidEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.MurkEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.ThicketEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.VerdantEntityRenderer;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class SkullBlockEntityRendererMixin
{
	@Inject(method = "method_3580", at = @At("RETURN"))
	private static void variantsandventures$addTextures(HashMap map, CallbackInfo ci) {
		map.put(SkullBlockType.GELID.get(), GelidEntityRenderer.TEXTURE);
		map.put(SkullBlockType.MURK.get(), MurkEntityRenderer.TEXTURES.get(MurkEntity.Variant.PURPLE));
		map.put(SkullBlockType.THICKET.get(), ThicketEntityRenderer.TEXTURE);
		map.put(SkullBlockType.VERDANT.get(), VerdantEntityRenderer.TEXTURE);
	}
}
