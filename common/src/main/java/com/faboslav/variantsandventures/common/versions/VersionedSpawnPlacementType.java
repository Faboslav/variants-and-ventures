package com.faboslav.variantsandventures.common.versions;


//? if >= 1.21 {
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
//?} else {
/*import net.minecraft.world.entity.SpawnPlacements;
*///?}


public class VersionedSpawnPlacementType
{
	//? if >=1.21 {
	public static final SpawnPlacementType IN_WATER = SpawnPlacementTypes.IN_WATER;
	//?} else {
	/*public static final SpawnPlacements.Type IN_WATER = SpawnPlacements.Type.IN_WATER;
	*///?}
}

