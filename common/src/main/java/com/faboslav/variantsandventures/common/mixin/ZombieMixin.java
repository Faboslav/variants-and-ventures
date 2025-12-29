package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.api.ZombieApi;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.network.packet.SyncZombieIsShakingPacketFromServer;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

//? if >= 1.21.5 {
import net.minecraft.world.entity.ConversionParams;
//?}

@Mixin(Zombie.class)
public abstract class ZombieMixin extends ZombieMonsterMixin implements ZombieApi
{
	@Unique
	private int variantsandventures$inPowderSnowTime;

	@Unique
	private int variantsandventures$conversionTime;

	@Unique
	private boolean variantsandventures$isConverting;

	public ZombieMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}


	@Unique
	public boolean variantsandventures$isFreezeConverting() {
		return this.variantsandventures$isConverting;
	}

	@Unique
	public void variantsandventures$setFreezeConverting(boolean isFrozen) {
		this.variantsandventures$isConverting = isFrozen;

		if(!this.level().isClientSide()) {
			SyncZombieIsShakingPacketFromServer.sendToClient(this, this.getId(), this.variantsandventures$isShaking());
		}
	}

	@Unique
	public boolean variantsandventures$isShaking() {
		return this.variantsandventures$isFreezeConverting();
	}


	@WrapMethod(
		method = "tick"
	)
	public void variantsandventures$tick(Operation<Void> original) {
		if (!this.level().isClientSide() && this.isAlive() && !this.isNoAi()) {
			if (this.isInPowderSnow) {
				if (this.variantsandventures$isFreezeConverting()) {
					--this.variantsandventures$conversionTime;
					if (this.variantsandventures$conversionTime < 0) {
						this.variantsandventures$doFreezeConversion();
					}
				} else {
					++this.variantsandventures$inPowderSnowTime;
					if (this.variantsandventures$inPowderSnowTime >= 140) {
						this.variantsandventures$startFreezeConversion(300);
					}
				}
			} else {
				this.variantsandventures$inPowderSnowTime = -1;

				if(this.variantsandventures$isFreezeConverting()) {
					this.variantsandventures$setFreezeConverting(false);
				}
			}
		}

		original.call();
	}

	@Unique
	private void variantsandventures$startFreezeConversion(int conversionTime) {
		this.variantsandventures$conversionTime = conversionTime;

		if(!this.variantsandventures$isFreezeConverting()) {
			this.variantsandventures$setFreezeConverting(true);
		}
	}

	@Unique
	protected void variantsandventures$doFreezeConversion() {
		//? if >= 1.21.5 {
		this.convertTo(VariantsAndVenturesEntityTypes.GELID.get(), ConversionParams.single((Zombie) (Object) this, true, true), gelid -> {
			if (!this.isSilent()) {
				this.level().levelEvent(null, 1048, this.blockPosition(), 0);
			}
		});
		//?} else {
		/*this.convertTo(VariantsAndVenturesEntityTypes.GELID.get(), true);
		if (!this.isSilent()) {
			this.level().levelEvent(null, 1048, this.blockPosition(), 0);
		}
		*///?}
	}

	@Override
	public boolean variantsandventures$canFreeze(Operation<Boolean> original) {
		return false;
	}
}
