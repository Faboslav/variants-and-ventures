package com.faboslav.variantsandventures.fabric.integrations;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.config.ConfigScreenBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.Screen;

public class CatalogueIntegration
{
	public static Screen createConfigScreen(Screen currentScreen, ModContainer container) {
		if (FabricLoader.getInstance().isModLoaded("catalogue") == false) {
			return null;
		}

		return ConfigScreenBuilder.createConfigScreen(VariantsAndVentures.getConfig(), currentScreen);
	}
}
