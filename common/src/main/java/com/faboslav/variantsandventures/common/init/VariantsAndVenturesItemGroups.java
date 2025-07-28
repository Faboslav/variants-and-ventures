package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import java.util.List;
import java.util.stream.Stream;

public class VariantsAndVenturesItemGroups
{
	public static final List<RegistryEntry<? extends Item>> CUSTOM_CREATIVE_TAB_ITEMS = List.of(
		VariantsAndVenturesItems.GELID_SPAWN_EGG,
		VariantsAndVenturesItems.MURK_SPAWN_EGG,
		VariantsAndVenturesItems.THICKET_SPAWN_EGG,
		VariantsAndVenturesItems.VERDANT_SPAWN_EGG
	);

	public static final ResourcefulRegistry<CreativeModeTab> ITEM_GROUPS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, VariantsAndVentures.MOD_ID);

	public static final RegistryEntry<CreativeModeTab> MAIN_TAB = ITEM_GROUPS.register("main_tab", () ->
		CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
			.title((Component.translatable("item_group." + VariantsAndVentures.MOD_ID + ".main_tab")))
			.icon(() -> {
				ItemStack iconStack = VariantsAndVenturesItems.GELID_SPAWN_EGG.get().getDefaultInstance();
				CompoundTag nbtCompound = new CompoundTag();
				nbtCompound.putBoolean("isCreativeTabIcon", true);
				iconStack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbtCompound));
				return iconStack;
			})
			.displayItems((itemDisplayParameters, entries) ->
				CUSTOM_CREATIVE_TAB_ITEMS.stream().map(item -> item.get().getDefaultInstance()).forEach(entries::accept)
			).build());

	public static void addItemGroupEntries(AddItemGroupEntriesEvent event) {
		if (event.type() == AddItemGroupEntriesEvent.Type.SPAWN_EGGS) {
			Stream.of(
				VariantsAndVenturesItems.GELID_SPAWN_EGG,
				VariantsAndVenturesItems.MURK_SPAWN_EGG,
				VariantsAndVenturesItems.THICKET_SPAWN_EGG,
				VariantsAndVenturesItems.VERDANT_SPAWN_EGG
			).map(item -> item.get().getDefaultInstance()).forEach(event::add);
		}
	}
}
