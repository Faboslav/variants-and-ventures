package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.entity.ai.GelidSnowballRangedAttackGoal;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import com.faboslav.variantsandventures.common.versions.VersionedEntitySpawnReason;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public final class GelidEntity extends Zombie
{
	public GelidEntity(EntityType<? extends Zombie> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createGelidAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0).add(Attributes.MOVEMENT_SPEED, 0.2).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.ARMOR, 2.0).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new GelidSnowballRangedAttackGoal(this, 10.0F));
		super.registerGoals();
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		this.setItemSlot(EquipmentSlot.OFFHAND, Items.SNOWBALL.getDefaultInstance());
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
		ItemStack itemStack = this.getItemInHand(InteractionHand.OFF_HAND);
		Item itemInHand = itemStack.getItem();

		if (itemInHand != Items.SNOWBALL) {
			return;
		}

		itemStack.shrink(1);

		/*? >=1.21.3 {*/
		Snowball snowball = new Snowball(this.level(), this, itemStack);
		/*?} else {*/
		/*Snowball snowball = new Snowball(this.level(), this);
		*//*?}*/
		double d = target.getEyeY() - 1.100000023841858;
		double e = target.getX() - this.getX();
		double f = d - snowball.getY();
		double g = target.getZ() - this.getZ();
		double h = Math.sqrt(e * e + g * g) * 0.20000000298023224;
		snowball.shoot(e, f + h, g, 1.6F, 7.0F);
		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.swing(InteractionHand.OFF_HAND);
		this.level().addFreshEntity(snowball);
	}

	@Override
	/*? >=1.21.3 {*/
	public boolean doHurtTarget(ServerLevel level, Entity source)
	/*?} else {*/
	/*public boolean doHurtTarget(Entity source)
	 *//*?}*/
	{
		this.level().broadcastEntityEvent(this, EntityEvent.START_ATTACKING);
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_GELID_ATTACK.get(), 1.0f, this.getVoicePitch());
		/*? >=1.21.3 {*/
		boolean attackResult = super.doHurtTarget(level, source);
		/*?} else {*/
		/*boolean attackResult = super.doHurtTarget(source);
		*//*?}*/

		if (
			attackResult && this.getMainHandItem().isEmpty()
			&& source instanceof LivingEntity
			&& source.canFreeze()
		) {
			float difficulty = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
			source.setTicksFrozen(140 * (int) difficulty);
		}

		return attackResult;
	}
}

