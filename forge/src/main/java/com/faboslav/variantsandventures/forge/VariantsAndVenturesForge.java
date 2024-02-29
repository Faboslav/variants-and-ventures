package com.faboslav.variantsandventures.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.events.client.RegisterItemColorEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityType;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesItems;
import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import com.faboslav.variantsandventures.common.platform.forge.*;
import net.minecraft.SharedConstants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.Map;
import java.util.function.Supplier;

@Mod(VariantsAndVentures.MOD_ID)
public final class VariantsAndVenturesForge
{
	public VariantsAndVenturesForge() {
		VariantsAndVentures.init();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			VariantsAndVenturesClient.init();
		}

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(VariantsAndVenturesForge::onSetup);
		modEventBus.addListener(VariantsAndVenturesForge::registerEntityAttributes);
		modEventBus.addListener(VariantsAndVenturesForge::registerItemColors);

		BlockRegistryImpl.BLOCKS.register(modEventBus);
		VariantsAndVenturesEntityType.previousUseChoiceTypeRegistrations = SharedConstants.useChoiceTypeRegistrations;
		SharedConstants.useChoiceTypeRegistrations = false;
		EntityTypeRegistryImpl.ENTITY_TYPES.register(modEventBus);
		SharedConstants.useChoiceTypeRegistrations = VariantsAndVenturesEntityType.previousUseChoiceTypeRegistrations;
		ItemRegistryImpl.ITEMS.register(modEventBus);
		SoundEventRegistryImpl.SOUND_EVENTS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private static void onSetup(FMLCommonSetupEvent event) {
		SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));
	}

	private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		for (Map.Entry<Supplier<? extends EntityType<? extends LivingEntity>>, Supplier<DefaultAttributeContainer.Builder>> entry : EntityAttributeRegistryImpl.ENTITY_ATTRIBUTES.entrySet()) {
			event.put(entry.getKey().get(), entry.getValue().get().build());
		}
	}

	private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		RegisterItemColorEvent.EVENT.invoke(new RegisterItemColorEvent(event::register, event.getBlockColors()::getColor));
		VariantsAndVenturesItems.SPAWN_EGGS.stream()
			.map(item -> (DispenserAddedSpawnEgg) item.get())
			.forEach(item -> event.register((stack, index) -> item.getColor(index), item));
	}
}
