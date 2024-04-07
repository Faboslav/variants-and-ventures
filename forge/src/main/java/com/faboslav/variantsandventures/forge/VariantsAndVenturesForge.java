package com.faboslav.variantsandventures.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.RegisterItemGroupsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntitySpawnRestrictionsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import com.faboslav.variantsandventures.common.init.registry.forge.ResourcefulRegistriesImpl;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

		modEventBus.addListener(EventPriority.NORMAL, ResourcefulRegistriesImpl::onRegisterForgeRegistries);
		VariantsAndVentures.init();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			VariantsAndVenturesClientForge.init(modEventBus, eventBus);
		}

		modEventBus.addListener(VariantsAndVenturesForge::onSetup);
		modEventBus.addListener(VariantsAndVenturesForge::onRegisterItemGroups);
		modEventBus.addListener(VariantsAndVenturesForge::onAddItemGroupEntries);
		modEventBus.addListener(VariantsAndVenturesForge::onRegisterAttributes);
		modEventBus.addListener(VariantsAndVenturesForge::onRegisterSpawnRestrictions);
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
				builder.entries((flag, output, bl) -> {
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

	private static void onRegisterSpawnRestrictions(SpawnPlacementRegisterEvent event) {
		RegisterEntitySpawnRestrictionsEvent.EVENT.invoke(new RegisterEntitySpawnRestrictionsEvent(VariantsAndVenturesForge.registerEntitySpawnRestriction(event)));
	}

	private static RegisterEntitySpawnRestrictionsEvent.Registrar registerEntitySpawnRestriction(
		SpawnPlacementRegisterEvent event
	) {
		return new RegisterEntitySpawnRestrictionsEvent.Registrar()
		{
			@Override
			public <T extends MobEntity> void register(
				EntityType<T> type,
				RegisterEntitySpawnRestrictionsEvent.Placement<T> placement
			) {
				event.register(type, placement.location(), placement.heightmap(), placement.predicate(), SpawnPlacementRegisterEvent.Operation.AND);
			}
		};
	}
}
