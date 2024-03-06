package com.faboslav.variantsandventures.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.RegisterItemGroupsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.List;

@Mod(VariantsAndVentures.MOD_ID)
public final class VariantsAndVenturesForge
{
	public VariantsAndVenturesForge() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus eventBus = MinecraftForge.EVENT_BUS;

		VariantsAndVentures.init();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			VariantsAndVenturesClientForge.init(modEventBus, eventBus);
		}

		modEventBus.addListener(VariantsAndVenturesForge::onSetup);
		modEventBus.addListener(VariantsAndVenturesForge::onRegisterItemGroups);
		modEventBus.addListener(VariantsAndVenturesForge::onAddItemGroupEntries);
		modEventBus.addListener(VariantsAndVenturesForge::onRegisterAttributes);
	}

	private static void onSetup(FMLCommonSetupEvent event) {
		SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));

		event.enqueueWork(() -> {
			VariantsAndVentures.lateInit();
		});
	}

	private static void onRegisterItemGroups(CreativeModeTabEvent.Register event) {
		RegisterItemGroupsEvent.EVENT.invoke(new RegisterItemGroupsEvent((id, operator, initialDisplayItems) ->
			event.registerCreativeModeTab(id, builder -> {
				operator.accept(builder);
				builder.entries((flag, output) -> {
					List<ItemStack> stacks = Lists.newArrayList();
					initialDisplayItems.accept(stacks);
					output.addAll(stacks);
				});
			})
		));
	}

	private static void onAddItemGroupEntries(CreativeModeTabEvent.BuildContents event) {
		AddItemGroupEntriesEvent.EVENT.invoke(new AddItemGroupEntriesEvent(AddItemGroupEntriesEvent.Type.toType(event.getTab()), event.getTab(), event.hasPermissions(), event::add));
	}

	private static void onRegisterAttributes(EntityAttributeCreationEvent event) {
		RegisterEntityAttributesEvent.EVENT.invoke(new RegisterEntityAttributesEvent((entity, builder) -> event.put(entity, builder.build())));
	}
}
