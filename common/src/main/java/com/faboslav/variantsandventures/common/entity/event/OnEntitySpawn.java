package com.faboslav.variantsandventures.common.entity.event;

import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.mixin.MobAccessor;
import com.faboslav.variantsandventures.common.versions.VersionedEntitySpawnReason;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

public final class OnEntitySpawn
{
	public static boolean handleOnEntitySpawn(
		EntitySpawnEvent event,
		EntityType<? extends Mob> entityTypeToReplace,
		EntityType<? extends Mob> entityTypeToSpawn,
		boolean spawnCondition,
		double spawnChanceCondition,
		int minimumSpawnYLevel,
		TagKey<Biome> biomeTagKey
	) {
		Mob entity = event.entity();
		RandomSource random = entity.getRandom();

		if (event.spawnReason() == VersionedEntitySpawnReason.NATURAL
			|| event.spawnReason() == VersionedEntitySpawnReason.CHUNK_GENERATION
			|| event.spawnReason() == VersionedEntitySpawnReason.STRUCTURE
		) {
			if (entity.getType() != entityTypeToReplace) {
				return false;
			}

			if (
				!spawnCondition
				|| random.nextFloat() >= spawnChanceCondition
				|| entity.blockPosition().getY() < minimumSpawnYLevel
			) {
				return false;
			}

			LevelAccessor world = event.worldAccess();
			Holder<Biome> biome = world.getBiome(entity.blockPosition());

			if (!biome.is(biomeTagKey)) {
				return false;
			}

			Mob entityToSpawn = entityTypeToSpawn.create(entity.level()/*? if >=1.21.3 {*/, event.spawnReason()/*?}*/);

			if (entityToSpawn == null) {
				return false;
			}

			//? if >=1.21.5 {
			entityToSpawn.snapTo(entity.getX(), entity.getY(), entity.getZ(), entityToSpawn.getRandom().nextFloat() * 360.0F, 0.0F);
			//?} else {
			/*entityToSpawn.moveTo(entity.getX(), entity.getY(), entity.getZ(), entityToSpawn.getRandom().nextFloat() * 360.0F, 0.0F);
			*///?}

			entityToSpawn.copyPosition(entity);
			entityToSpawn.yBodyRotO = entity.yBodyRotO;
			entityToSpawn.yBodyRot = entity.yBodyRot;
			entityToSpawn.yHeadRotO = entity.yHeadRotO;
			entityToSpawn.yHeadRot = entity.yHeadRot;
			entityToSpawn.setBaby(entity.isBaby());
			entityToSpawn.setNoAi(entity.isNoAi());
			entityToSpawn.setInvulnerable(entity.isInvulnerable());

			if(entity.hasCustomName()) {
				entityToSpawn.setCustomName(entity.getCustomName());
				entityToSpawn.setCustomNameVisible(entity.isCustomNameVisible());
			}

			if (entity.isPersistenceRequired()) {
				entityToSpawn.setPersistenceRequired();
			}

			entityToSpawn.setCanPickUpLoot(entity.canPickUpLoot());

			for(EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
				ItemStack itemStack = entity.getItemBySlot(equipmentSlot);
				if (!itemStack.isEmpty()) {
					entityToSpawn.setItemSlot(equipmentSlot, itemStack.copyAndClear());
					float dropChance;

					//? if >= 1.21.5 {
					dropChance = entity.getDropChances().byEquipment(equipmentSlot);
					//?} else {
					/*dropChance = ((MobAccessor) entity).variantsandventures$getEquipmentDropChance(equipmentSlot);
					*///?}
					entityToSpawn.setDropChance(equipmentSlot, dropChance);
				}
			}

			entityToSpawn.finalizeSpawn(
				(ServerLevelAccessor) world,
				((ServerLevelAccessor)world).getCurrentDifficultyAt(entity.blockPosition()),
				event.spawnReason(),
				null
				//? < 1.21.1 {
				/*, null
				*///?}
			);

			boolean spawnResult = world.addFreshEntity(entityToSpawn);

			if(!spawnResult) {
				entity.discard();
				return false;
			}

			return true;
		}

		return false;
	}
}
