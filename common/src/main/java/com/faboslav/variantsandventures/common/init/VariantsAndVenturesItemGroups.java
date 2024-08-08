package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

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

	public static final ResourcefulRegistry<ItemGroup> ITEM_GROUPS = ResourcefulRegistries.create(Registries.ITEM_GROUP, VariantsAndVentures.MOD_ID);

	public static final RegistryEntry<ItemGroup> MAIN_TAB = ITEM_GROUPS.register("main_tab", () ->
		ItemGroup.create(ItemGroup.Row.TOP, 0)
			.displayName((Text.translatable("item_group." + VariantsAndVentures.MOD_ID + ".main_tab")))
			.icon(() -> {
				ItemStack iconStack = VariantsAndVenturesItems.GELID_SPAWN_EGG.get().getDefaultStack();
				NbtCompound nbtCompound = new NbtCompound();
				nbtCompound.putBoolean("isCreativeTabIcon", true);
				iconStack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbtCompound));
				return iconStack;
			})
			.entries((itemDisplayParameters, entries) ->
				CUSTOM_CREATIVE_TAB_ITEMS.stream().map(item -> item.get().getDefaultStack()).forEach(entries::add)
			).build());

	public static void addItemGroupEntries(AddItemGroupEntriesEvent event) {
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
