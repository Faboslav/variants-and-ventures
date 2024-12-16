package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.structure.pool.alias.StructurePoolAliasBinding;
import net.minecraft.world.gen.structure.JigsawStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(JigsawStructure.class)
public interface JigsawStructureAccessor
{
	@Accessor("poolAliasBindings")
	List<StructurePoolAliasBinding> getPoolAliasBindings();

	@Accessor("poolAliasBindings")
	void setPoolAliasBindings(List<StructurePoolAliasBinding> poolAliasBindings);
}