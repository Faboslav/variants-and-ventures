package com.faboslav.variantsandventures.fabric.integrations;

//? if modMenu {
/*import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public final class ModMenuIntegration implements ModMenuApi
{
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return (screen) -> {
			if (!FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
				return null;
			}

			return VariantsAndVenturesClient.getConfigScreen(screen);
		};
	}
}
*///?}