package com.faboslav.variantsandventures.common.platform.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public final class ConfigDirectoryImpl
{
	/**
	 * @see com.faboslav.variantsandventures.platform.ConfigDirectory#getConfigDirectory()
	 */
	public static Path getConfigDirectory() {
		return FabricLoader.getInstance().getConfigDir();
	}

	private ConfigDirectoryImpl() {
	}
}
