package com.faboslav.variantsandventures.common.platform.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public final class ConfigDirectoryImpl
{
	public static Path getConfigDirectory() {
		return FabricLoader.getInstance().getConfigDir();
	}

	private ConfigDirectoryImpl() {
	}
}
