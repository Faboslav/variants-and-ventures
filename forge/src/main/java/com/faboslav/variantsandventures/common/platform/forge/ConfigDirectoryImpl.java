package com.faboslav.variantsandventures.common.platform.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class ConfigDirectoryImpl
{
	/**
	 * @see com.faboslav.variantsandventures.platform.ConfigDirectory#getConfigDirectory()
	 */
	public static Path getConfigDirectory() {
		return FMLPaths.CONFIGDIR.get();
	}

	private ConfigDirectoryImpl() {
	}
}
