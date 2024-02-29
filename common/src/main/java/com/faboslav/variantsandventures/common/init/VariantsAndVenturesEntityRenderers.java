package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.client.render.entity.GelidEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.ThicketEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.VerdantEntityRenderer;
import com.faboslav.variantsandventures.common.platform.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderers;

/**
 * @see EntityRenderers
 */
@Environment(EnvType.CLIENT)
public final class VariantsAndVenturesEntityRenderers
{
	public static void postInit() {
		EntityRendererRegistry.register(VariantsAndVenturesEntityType.GELID, GelidEntityRenderer::new);
		EntityRendererRegistry.register(VariantsAndVenturesEntityType.THICKET, ThicketEntityRenderer::new);
		EntityRendererRegistry.register(VariantsAndVenturesEntityType.VERDANT, VerdantEntityRenderer::new);
	}

	private VariantsAndVenturesEntityRenderers() {
	}
}
