package com.faboslav.variantsandventures.common.events.entity;

import com.faboslav.variantsandventures.common.events.base.CancellableEventHandler;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.function.Predicate;

//? if >=1.21.3 {
import net.minecraft.world.entity.EntitySpawnReason;
//?} else {
/*import net.minecraft.world.entity.MobSpawnType;
*///?}
/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
//? if >=1.21.3 {
public record EntitySpawnEvent(Mob entity, LevelAccessor worldAccess, boolean isBaby, EntitySpawnReason spawnReason, Predicate<Mob> replacementHandler)
//?} else {
/*public record EntitySpawnEvent(Mob entity, LevelAccessor worldAccess, boolean isBaby, MobSpawnType spawnReason, Predicate<Mob> replacementHandler)
*///?}
{
	public static final CancellableEventHandler<EntitySpawnEvent> EVENT = new CancellableEventHandler<>();

	public EntitySpawnEvent(Mob entity, LevelAccessor worldAccess, boolean isBaby, /*? if >=1.21.3 {*/EntitySpawnReason/*?} else {*//*MobSpawnType*//*?}*/ spawnReason) {
		this(entity, worldAccess, isBaby, spawnReason, null);
	}

	public boolean replaceWith(Mob replacement) {
		if (replacementHandler != null) {
			return replacementHandler.test(replacement);
		}

		replacement.finalizeSpawn(
			(ServerLevelAccessor) worldAccess,
			((ServerLevelAccessor) worldAccess).getCurrentDifficultyAt(entity.blockPosition()),
			spawnReason,
			null
			//? < 1.21.1 {
			/*, null
			*///?}
		);

		return worldAccess.addFreshEntity(replacement);
	}
}
