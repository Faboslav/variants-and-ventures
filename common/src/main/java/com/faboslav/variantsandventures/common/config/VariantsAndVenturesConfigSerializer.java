package com.faboslav.variantsandventures.common.config;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

public final class VariantsAndVenturesConfigSerializer
{
	private static final Path CONFIG_PATH = Path.of("config", VariantsAndVentures.MOD_ID + ".json");
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void load() {
		try {
			if (Files.exists(CONFIG_PATH)) {
				VariantsAndVenturesConfig config = VariantsAndVentures.getConfig();
				VariantsAndVenturesConfig loadedConfig = GSON.fromJson(Files.readString(CONFIG_PATH), VariantsAndVenturesConfig.class);

				for (Field field : VariantsAndVenturesConfig.class.getDeclaredFields()) {
					if (Modifier.isStatic(field.getModifiers())) continue;
					field.setAccessible(true);
					field.set(config, field.get(loadedConfig));
				}
			} else {
				save();
			}
		} catch (Exception e) {
			VariantsAndVentures.getLogger().error("Failed to load config.", e);
		}
	}

	public static void save() {
		try {
			Files.createDirectories(CONFIG_PATH.getParent());
			Files.writeString(CONFIG_PATH, GSON.toJson(VariantsAndVentures.getConfig()));
		} catch (Exception e) {
			VariantsAndVentures.getLogger().error("Failed to save config.", e);
		}
	}
}
