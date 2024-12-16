package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.client.render.entity.GelidEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.MurkEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.ThicketEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.VerdantEntityRenderer;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;

/**
 * @see EntityRenderers
 */
@Environment(EnvType.CLIENT)
public final class VariantsAndVenturesEntityRenderers
{
	public static void init(RegisterEntityRenderersEvent event) {
		event.register(VariantsAndVenturesEntityTypes.GELID.get(), GelidEntityRenderer::new);
		event.register(VariantsAndVenturesEntityTypes.MURK.get(), MurkEntityRenderer::new);
		event.register(VariantsAndVenturesEntityTypes.THICKET.get(), ThicketEntityRenderer::new);
		event.register(VariantsAndVenturesEntityTypes.VERDANT.get(), VerdantEntityRenderer::new);
	}

	private VariantsAndVenturesEntityRenderers() {
	}
}
