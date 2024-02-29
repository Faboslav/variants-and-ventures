package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import net.fabricmc.api.ModInitializer;

public final class VariantsAndVenturesFabric implements ModInitializer
{
	@Override
	public void onInitialize() {
		VariantsAndVentures.init();
		VariantsAndVentures.lateInit();

		initEvents();
	}

	private void initEvents() {
		SetupEvent.EVENT.invoke(new SetupEvent(Runnable::run));
	}
}
