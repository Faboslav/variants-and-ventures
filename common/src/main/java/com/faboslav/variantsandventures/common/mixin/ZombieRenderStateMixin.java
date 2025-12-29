package com.faboslav.variantsandventures.common.mixin;

import org.spongepowered.asm.mixin.Mixin;

//? if >= 1.21.3 {
import com.faboslav.variantsandventures.common.api.ZombieApi;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ZombieRenderState.class)
public abstract class ZombieRenderStateMixin implements ZombieApi
{
	@Unique
	public boolean variantsandventures$isShaking;

	public void variantsandventures$setFreezeConverting(boolean isFrozen) {
		this.variantsandventures$isShaking = isFrozen;
	}

	public boolean variantsandventures$isShaking() {
		return this.variantsandventures$isShaking;
	}
}
//?} else {
/*import net.minecraft.world.entity.monster.Zombie;

@Mixin(Zombie.class)
public abstract class ZombieRenderStateMixin {
}
*///?}
