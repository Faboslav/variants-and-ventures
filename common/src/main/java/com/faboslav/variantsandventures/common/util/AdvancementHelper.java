package com.faboslav.variantsandventures.common.util;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public final class AdvancementHelper
{
	public static void triggerMonsterHunter(Level level, DamageSource damageSource) {
		if(level.isClientSide()) {
			return;
		}

		var server = level.getServer();

		if(server == null) {
			return;
		}

		ResourceLocation advancementId = VariantsAndVentures.makeNamespacedId("minecraft:adventure/kill_a_mob");

		//? if >= 1.21.1 {
		var advancement = server.getAdvancements().get(advancementId);
		//?} else {
		/*var advancement = server.getAdvancements().getAdvancement(advancementId);
		*///?}

		if (advancement == null){
			return;
		}

		if(!(damageSource.getEntity() instanceof ServerPlayer player)) {
			return;
		}

		AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
		if (progress.isDone()) {
			return;
		}

		for (String criterion : progress.getRemainingCriteria()) {
			player.getAdvancements().award(advancement, criterion);
		}
	}
}
