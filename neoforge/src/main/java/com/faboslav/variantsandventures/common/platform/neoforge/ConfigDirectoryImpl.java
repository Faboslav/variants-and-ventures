package com.faboslav.variantsandventures.common.platform.neoforge;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class ConfigDirectoryImpl
{
	public static Path getConfigDirectory() {
		return FMLPaths.CONFIGDIR.get();
	}

	private ConfigDirectoryImpl() {
	}
}
