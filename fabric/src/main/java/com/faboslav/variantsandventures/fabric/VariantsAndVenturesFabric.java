package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.RegisterItemGroupsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.List;

public final class VariantsAndVenturesFabric implements ModInitializer
{
	@Override
	public void onInitialize() {
		VariantsAndVentures.init();

		initEvents();

		VariantsAndVentures.lateInit();
	}

	private void initEvents() {
		RegisterEntityAttributesEvent.EVENT.invoke(new RegisterEntityAttributesEvent(FabricDefaultAttributeRegistry::register));
		SetupEvent.EVENT.invoke(new SetupEvent(Runnable::run));

		RegisterItemGroupsEvent.EVENT.invoke(new RegisterItemGroupsEvent((id, initializer, initialDisplayItems) -> {
			ItemGroup.Builder builder = FabricItemGroup.builder(id);
			initializer.accept(builder);
			builder.entries((flags, output, bl) -> {
				List<ItemStack> stacks = Lists.newArrayList();
				initialDisplayItems.accept(stacks);
				output.addAll(stacks);
			});
			builder.build();
		}));

		ItemGroupEvents.MODIFY_ENTRIES_ALL.register((itemGroup, entries) ->
			AddItemGroupEntriesEvent.EVENT.invoke(
				new AddItemGroupEntriesEvent(
					AddItemGroupEntriesEvent.Type.toType(itemGroup),
					itemGroup,
					itemGroup.hasStacks(),
					entries::add
				)
			)
		);
	}
}
