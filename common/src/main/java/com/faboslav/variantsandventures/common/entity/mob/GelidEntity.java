package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.entity.ai.GelidSnowballRangedAttackGoal;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public final class GelidEntity extends ZombieEntity
{
	public GelidEntity(EntityType<? extends ZombieEntity> entityType, World world) {
		super(entityType, world);
	}

	public static boolean canSpawn(
		EntityType<StrayEntity> type,
		ServerWorldAccess world,
		SpawnReason spawnReason,
		BlockPos pos,
		Random random
	) {
		return canSpawnInDark(type, world, spawnReason, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(pos));
	}

	public static DefaultAttributeContainer.Builder createGelidAttributes() {
		return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0).add(EntityAttributes.GENERIC_ARMOR, 2.0).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new GelidSnowballRangedAttackGoal(this, 10.0F));
		super.initGoals();
	}

	@Override
	protected void initEquipment(Random random, LocalDifficulty difficulty) {
		super.initEquipment(random, difficulty);
		this.equipStack(EquipmentSlot.OFFHAND, Items.SNOWBALL.getDefaultStack());
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_GELID_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VariantsAndVenturesSoundEvents.ENTITY_GELID_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_GELID_DEATH.get();
	}

	@Override
	protected SoundEvent getStepSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_GELID_STEP.get();
	}

	public void throwSnowball(LivingEntity target, float pullProgress) {
		ItemStack itemStack = this.getStackInHand(Hand.OFF_HAND);
		Item itemInHand = itemStack.getItem();

		if (itemInHand != Items.SNOWBALL) {
			return;
		}

		itemStack.decrement(1);

		SnowballEntity snowballEntity = new SnowballEntity(this.getWorld(), this);
		double d = target.getEyeY() - 1.100000023841858;
		double e = target.getX() - this.getX();
		double f = d - snowballEntity.getY();
		double g = target.getZ() - this.getZ();
		double h = Math.sqrt(e * e + g * g) * 0.20000000298023224;
		snowballEntity.setVelocity(e, f + h, g, 1.6F, 7.0F);
		this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.swingHand(Hand.OFF_HAND);
		this.getWorld().spawnEntity(snowballEntity);
	}

	@Override
	public boolean tryAttack(Entity target) {
		this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_GELID_ATTACK.get(), 1.0f, this.getSoundPitch());
		boolean attackResult = super.tryAttack(target);

		if (attackResult && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
			float difficulty = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();

			if (target instanceof LivingEntity livingEntity) {
				if (
					livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_HELMET)
					|| livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_CHESTPLATE)
					|| livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_LEGGINGS)
					|| livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS)
				) {
					return attackResult;
				}
			}

			target.setFrozenTicks(140 * (int) difficulty);
		}

		return attackResult;
	}
}

