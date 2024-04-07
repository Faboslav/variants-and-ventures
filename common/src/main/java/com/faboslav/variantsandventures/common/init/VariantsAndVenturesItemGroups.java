package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.RegisterItemGroupsEvent;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import java.util.List;
import java.util.stream.Stream;

public class VariantsAndVenturesItemGroups
{
	public static final List<RegistryEntry<? extends Item>> CUSTOM_CREATIVE_TAB_ITEMS = List.of(
		VariantsAndVenturesItems.GELID_HEAD,
		VariantsAndVenturesItems.MURK_SKULL,
		VariantsAndVenturesItems.THICKET_HEAD,
		VariantsAndVenturesItems.VERDANT_SKULL,
		VariantsAndVenturesItems.GELID_SPAWN_EGG,
		VariantsAndVenturesItems.MURK_SPAWN_EGG,
		VariantsAndVenturesItems.THICKET_SPAWN_EGG,
		VariantsAndVenturesItems.VERDANT_SPAWN_EGG
	);

	public static void registerItemGroups(RegisterItemGroupsEvent event) {
		event.register(
			VariantsAndVentures.makeID("main_tab"),
			builder -> builder.icon(() -> VariantsAndVenturesItems.GELID_HEAD.get().getDefaultStack()).displayName(Text.translatable("item_group." + VariantsAndVentures.MOD_ID + ".main_tab")),
			items -> CUSTOM_CREATIVE_TAB_ITEMS.stream().map(item -> item.get().getDefaultStack()).forEach(items::add)

		);
	}

	public static void addItemGroupEntries(AddItemGroupEntriesEvent event) {
		if (event.type() == AddItemGroupEntriesEvent.Type.FUNCTIONAL) {
			Stream.of(
				VariantsAndVenturesItems.GELID_HEAD,
				VariantsAndVenturesItems.MURK_SKULL,
				VariantsAndVenturesItems.THICKET_HEAD,
				VariantsAndVenturesItems.VERDANT_SKULL
			).map(item -> item.get().getDefaultStack()).forEach(event::add);
		}

		if (event.type() == AddItemGroupEntriesEvent.Type.SPAWN_EGGS) {
			Stream.of(
				VariantsAndVenturesItems.GELID_SPAWN_EGG,
				VariantsAndVenturesItems.MURK_SPAWN_EGG,
				VariantsAndVenturesItems.THICKET_SPAWN_EGG,
				VariantsAndVenturesItems.VERDANT_SPAWN_EGG
			).map(item -> item.get().getDefaultStack()).forEach(event::add);
		}
	}
}
