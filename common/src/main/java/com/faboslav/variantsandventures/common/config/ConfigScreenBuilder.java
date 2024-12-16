package com.faboslav.variantsandventures.common.config;

import com.faboslav.variantsandventures.common.config.annotation.Category;
import com.faboslav.variantsandventures.common.config.annotation.Description;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class ConfigScreenBuilder
{
	public static Screen createConfigScreen(VariantsAndVenturesConfig config, Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
			.setTitle(Component.literal("Variants & Ventures"))
			.setSavingRunnable(config::save)
			.setParentScreen(parent);

		ConfigCategory category = null;

		try {
			for (Field field : config.getClass().getDeclaredFields()) {
				String categoryString = getFieldCategory(field);

				if (categoryString != null) {
					category = builder.getOrCreateCategory(Component.literal(getFieldCategory(field)));
				}

				if (category == null) {
					throw new RuntimeException("Missing category annotation.");
				}

				if (field.getGenericType() == boolean.class) {
					category.addEntry(builder.entryBuilder()
						.startBooleanToggle(Component.literal(getFieldDescription(field)), field.getBoolean(config))
						.setDefaultValue(field.getBoolean(new VariantsAndVenturesConfig()))
						.setSaveConsumer((enabled) -> {
							try {
								field.set(config, enabled);
							} catch (IllegalAccessException e) {
								throw new RuntimeException(e);
							}
						})
						.build()
					);
				} else if (field.getGenericType() == int.class) {
					category.addEntry(builder.entryBuilder()
						.startIntField(Component.literal(getFieldDescription(field)), field.getInt(config))
						.setDefaultValue(field.getInt(new VariantsAndVenturesConfig()))
						.setSaveConsumer((enabled) -> {
							try {
								field.set(config, enabled);
							} catch (IllegalAccessException e) {
								throw new RuntimeException(e);
							}
						})
						.build()
					);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException exception) {
			// ignored
		}

		return builder.build();
	}

	@Nullable
	private static String getFieldCategory(Field field) {
		Annotation[] annotations = field.getDeclaredAnnotations();

		for (Annotation annotation : annotations) {
			if (annotation instanceof Category) {
				return ((Category) annotation).value();
			}
		}

		return null;
	}

	private static String getFieldDescription(Field field) {
		Annotation[] annotations = field.getDeclaredAnnotations();

		for (Annotation annotation : annotations) {
			if (annotation instanceof Description) {
				return ((Description) annotation).value();
			}
		}

		throw new RuntimeException("Field " + field.getName() + " is missing description annotation.");
	}
}
