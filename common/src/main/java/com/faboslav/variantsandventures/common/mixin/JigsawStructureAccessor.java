package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(JigsawStructure.class)
public interface JigsawStructureAccessor
{
	@Accessor("poolAliases")
	List<PoolAliasBinding> getPoolAliasBindings();

	@Accessor("poolAliases")
	void setPoolAliasBindings(List<PoolAliasBinding> poolAliasBindings);
}