package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.ai.goal.LeaveWaterGoal;
import com.faboslav.variantsandventures.common.entity.ai.goal.TargetAboveWaterGoal;
import com.faboslav.variantsandventures.common.entity.ai.goal.WanderAroundOnSurfaceGoal;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import com.faboslav.variantsandventures.common.versions.VersionedInteractionResult;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/*? >=1.21.3 {*/
import net.minecraft.world.entity.EntitySpawnReason;
/*?} else {*/
/*import net.minecraft.world.entity.MobSpawnType;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import java.util.Locale;
import java.util.function.Predicate;
import com.faboslav.variantsandventures.common.versions.VersionedGameRulesProvider;
*//*?}*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public final class MurkEntity extends AbstractSkeleton implements Shearable
{
	private static final EntityDataAccessor<Integer> VARIANT;
	private static final EntityDataAccessor<Boolean> SHEARED;
	public static final String VARIANT_NBT_KEY = "Variant";
	public static final String SHEARED_NBT_KEY = "Sheared";

	private boolean targetingUnderwater;
	private final WaterBoundPathNavigation waterNavigation;
	private final GroundPathNavigation landNavigation;
	/*? <1.21.3 {*/
	/*private final Predicate<LivingEntity> PLAYER_FILTER = (LivingEntity entity) -> {
		if (entity != null) {
			return !this.level().isDay() || entity.isInWater();
		} else {
			return false;
		}
	};
	*//*?}*/

	public MurkEntity(EntityType<? extends AbstractSkeleton> entityType, Level world) {
		super(entityType, world);
		this.moveControl = new MurkMoveControl(this);
		this.setPathfindingMalus(PathType.WATER, 0.0F);
		this.waterNavigation = new WaterBoundPathNavigation(this, world);
		this.landNavigation = new GroundPathNavigation(this, world);
	}

	@Override
	public SpawnGroupData finalizeSpawn(
		ServerLevelAccessor world,
		DifficultyInstance difficulty,
		/*? >=1.21.3 {*/
		EntitySpawnReason spawnReason,
		/*?} else {*/
		/*MobSpawnType spawnReason,
		 *//*?}*/
		@Nullable SpawnGroupData entityData
	) {
		this.setVariant(Variant.getRandom(random));

		return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}

	public static boolean canSpawn(
		EntityType<MurkEntity> type,
		ServerLevelAccessor world,
		/*? >=1.21.3 {*/
		EntitySpawnReason spawnReason,
		/*?} else {*/
		/*MobSpawnType spawnReason,
		 *//*?}*/
		BlockPos pos,
		RandomSource random
	) {
		return world.getFluidState(pos.below()).is(FluidTags.WATER)
			   && isValidSpawnDepth(world, pos)
			   && random.nextInt(40) == 0;
	}

	private static boolean isValidSpawnDepth(LevelAccessor world, BlockPos pos) {
		return pos.getY() < world.getSeaLevel() - 5;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new WanderAroundOnSurfaceGoal(this, 1.0));
		this.goalSelector.addGoal(5, new LeaveWaterGoal(this, 1.0));
		this.goalSelector.addGoal(6, new TargetAboveWaterGoal(this, 1.0, this.level().getSeaLevel()));
		this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		/*? >=1.21.3 {*/
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, (livingEntity, serverLevel) -> {
			return this.canAttackTarget(livingEntity);
		}));
		/*?} else {*/
		/*this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, PLAYER_FILTER));
		*//*?}*/
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Axolotl.class, true, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));

		super.registerGoals();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(VARIANT, 0);
		builder.define(SHEARED, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt(VARIANT_NBT_KEY, this.getVariant().getId());
		nbt.putBoolean(SHEARED_NBT_KEY, this.isSheared());

	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		this.setVariant(Variant.VARIANTS[nbt.getInt(VARIANT_NBT_KEY)]);
		this.setSheared(nbt.getBoolean(SHEARED_NBT_KEY));
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return world.isUnobstructed(this);
	}

	public static AttributeSupplier.Builder createMurkAttributes() {
		return AbstractSkeleton.createAttributes().add(Attributes.MAX_HEALTH, 16.0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_AMBIENT_WATER.get():VariantsAndVenturesSoundEvents.ENTITY_MURK_AMBIENT.get();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if (soundEvent != null) {
			this.playSound(soundEvent, this.isInWater() ? 0.25F:this.getSoundVolume(), this.getVoicePitch());
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isInWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_HURT_WATER.get():VariantsAndVenturesSoundEvents.ENTITY_MURK_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isInWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_DEATH_WATER.get():VariantsAndVenturesSoundEvents.ENTITY_MURK_DEATH.get();
	}

	public SoundEvent getStepSound() {
		return this.isInWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_STEP.get():SoundEvents.SKELETON_STEP;
	}

	@Override
	public void tick() {
		if (!VariantsAndVentures.getConfig().enableMurk) {
			this.discard();
		}

		super.tick();
	}

	@Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
		ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));
		ItemStack itemStack2 = this.getProjectile(itemStack);
		AbstractArrow persistentProjectileEntity = this.getArrow(itemStack2, pullProgress, itemStack);
		double d = target.getX() - this.getX();
		double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
		double f = target.getZ() - this.getZ();
		double g = Math.sqrt(d * d + f * f);
		persistentProjectileEntity.shoot(d, e + g * 0.20000000298023224, f, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_MURK_ATTACK.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(persistentProjectileEntity);
	}

	@Override
	public boolean isPushedByFluid() {
		return !this.isSwimming();
	}

	@Override
	public boolean shouldDiscardFriction() {
		return this.isSwimming();
	}

	private boolean isTargetingUnderwater() {
		if (this.targetingUnderwater) {
			return true;
		} else {
			LivingEntity livingEntity = this.getTarget();
			return livingEntity != null && livingEntity.isInWater();
		}
	}

	public void setTargetingUnderwater(boolean targetingUnderwater) {
		this.targetingUnderwater = targetingUnderwater;
	}

	@Override
	public void travel(Vec3 movementInput) {
		if (this.isEffectiveAi() && this.isInWater() && this.isTargetingUnderwater()) {
			this.moveRelative(0.01F, movementInput);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
		} else {
			super.travel(movementInput);
		}
	}

	@Override
	public void updateSwimming() {
		if (!this.level().isClientSide) {
			if (this.isEffectiveAi() && this.isInWater() && this.isTargetingUnderwater()) {
				this.navigation = this.waterNavigation;
				this.setSwimming(true);
			} else {
				this.navigation = this.landNavigation;
				this.setSwimming(false);
			}
		}
	}

	public boolean hasFinishedCurrentPath() {
		Path path = this.getNavigation().getPath();
		if (path != null) {
			BlockPos blockPos = path.getTarget();
			if (blockPos != null) {
				double d = this.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getZ());
				return d < 4.0;
			}
		}

		return false;
	}

	public boolean isSheared() {
		return this.entityData.get(SHEARED);
	}

	public void setSheared(boolean sheared) {
		this.entityData.set(SHEARED, sheared);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		if (itemStack.is(Items.SHEARS) && this.readyForShearing()) {
			if (!this.level().isClientSide()) {
				/*? >=1.21.3 {*/
				this.shear(((ServerLevel) this.level()), SoundSource.PLAYERS, itemStack);
				/*?} else {*/
				/*this.shear(SoundSource.PLAYERS);
				*//*?}*/
				this.gameEvent(GameEvent.SHEAR, player);
				itemStack.hurtAndBreak(1, player, Player.getSlotForHand(hand));
			}

			return VersionedInteractionResult.success(this);
		} else {
			return super.mobInteract(player, hand);
		}
	}

	/*? >=1.21.3 {*/
	@Override
	public void shear(ServerLevel level, SoundSource soundSource, ItemStack shears) {
		this.level().playSound(null, this, VariantsAndVenturesSoundEvents.ENTITY_MURK_SHEAR.get(), soundSource, 1.0F, 1.0F);
		this.dropShearedItems(level, shears);
		this.setSheared(true);
	}

	private void dropShearedItems(ServerLevel level, ItemStack stack) {
		var shearingLootTableResourceKey = ResourceKey.create(Registries.LOOT_TABLE, VariantsAndVentures.makeID(String.format(Locale.ROOT, "shearing/murk_%s", this.getVariant().getName())));

		this.dropFromShearingLootTable(level, shearingLootTableResourceKey, stack, (serverLevel, itemStack) -> {
			this.spawnAtLocation(serverLevel, itemStack, this.getBbHeight());
		});
	}
	/*?} else {*/

	/*@Override
	public void shear(SoundSource soundSource) {
		this.level().playSound(null, this, VariantsAndVenturesSoundEvents.ENTITY_MURK_SHEAR.get(), soundSource, 1.0F, 1.0F);
		this.dropShearedItems();
		this.setSheared(true);
	}

	private void dropShearedItems() {
		Level world = this.level();

		if (
			!(world instanceof ServerLevel)
			|| !VersionedGameRulesProvider.getGameRules(this).getBoolean(GameRules.RULE_DOMOBLOOT)
		) {
			return;
		}

		LootTable shearingLootTable = world.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, VariantsAndVentures.makeID(String.format(Locale.ROOT, "shearing/murk_%s", this.getVariant().getName()))));
		LootParams lootContextParameterSet = new LootParams.Builder((ServerLevel) world)
			.withParameter(LootContextParams.ORIGIN, this.position())
			.withParameter(LootContextParams.THIS_ENTITY, this)
			.create(LootContextParamSets.GIFT);
		ObjectArrayList<ItemStack> shearingDrops = shearingLootTable.getRandomItems(lootContextParameterSet);

		for (ItemStack shearingDrop : shearingDrops) {
			this.spawnAtLocation(shearingDrop);
		}
	}
	*//*?}*/

	@Override
	public boolean readyForShearing() {
		return !this.isSheared() && this.isAlive();
	}

	public boolean canAttackTarget(@Nullable LivingEntity target) {
		if (target != null) {
			return !this.level().isDay() || target.isInWater();
		} else {
			return false;
		}
	}

	private final class MurkMoveControl extends MoveControl
	{
		private final MurkEntity murk;

		public MurkMoveControl(MurkEntity murk) {
			super(murk);
			this.murk = murk;
		}

		public void tick() {
			LivingEntity livingEntity = this.murk.getTarget();
			if (this.murk.isTargetingUnderwater() && this.murk.isInWater()) {
				if (livingEntity != null && livingEntity.getY() > this.murk.getY() || this.murk.targetingUnderwater) {
					this.murk.setDeltaMovement(this.murk.getDeltaMovement().add(0.0, 0.002, 0.0));
				}

				if (this.operation != Operation.MOVE_TO || this.murk.getNavigation().isDone()) {
					this.murk.setSpeed(0.0F);
					return;
				}

				double d = this.wantedX - this.murk.getX();
				double e = this.wantedY - this.murk.getY();
				double f = this.wantedZ - this.murk.getZ();
				double g = Math.sqrt(d * d + e * e + f * f);
				e /= g;
				float h = (float) (Mth.atan2(f, d) * 57.2957763671875) - 90.0F;
				this.murk.setYRot(this.rotlerp(this.murk.getYRot(), h, 90.0F));
				this.murk.yBodyRot = this.murk.getYRot();
				float i = (float) (this.speedModifier * this.murk.getAttributeValue(Attributes.MOVEMENT_SPEED));
				float j = Mth.lerp(0.125F, this.murk.getSpeed(), i);
				this.murk.setSpeed(j);
				this.murk.setDeltaMovement(this.murk.getDeltaMovement().add((double) j * d * 0.005, (double) j * e * 0.1, (double) j * f * 0.005));
			} else {
				if (!this.murk.onGround()) {
					this.murk.setDeltaMovement(this.murk.getDeltaMovement().add(0.0, -0.008, 0.0));
				}

				super.tick();
			}

		}
	}

	private void setNavigation(PathNavigation navigation) {
		this.navigation = navigation;
	}

	public void setLandNavigation() {
		this.navigation = this.landNavigation;
	}

	public void setWaterNavigation() {
		this.navigation = this.waterNavigation;
	}

	public Variant getVariant() {
		return Variant.VARIANTS[this.entityData.get(VARIANT)];
	}

	private void setVariant(Variant variant) {
		this.entityData.set(VARIANT, variant.getId());
	}

	public enum Variant
	{
		PURPLE(0, "purple"),
		RED(1, "red"),
		YELLOW(2, "yellow");

		public static final MurkEntity.Variant[] VARIANTS = Arrays.stream(values()).sorted(Comparator.comparingInt(MurkEntity.Variant::getId)).toArray(Variant[]::new);
		private final int id;
		private final String name;

		Variant(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}

		private static Variant getRandom(RandomSource random) {
			return Util.getRandom(Arrays.stream(VARIANTS).toArray(Variant[]::new), random);
		}
	}

	static {
		VARIANT = SynchedEntityData.defineId(MurkEntity.class, EntityDataSerializers.INT);
		SHEARED = SynchedEntityData.defineId(MurkEntity.class, EntityDataSerializers.BOOLEAN);
	}
}