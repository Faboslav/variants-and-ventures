package com.faboslav.variantsandventures.common.versions;

//? if >= 1.21 {
import net.minecraft.world.level.pathfinder.PathType;
//?} else {
/*import net.minecraft.world.level.pathfinder.BlockPathTypes;
*///?}
public class VersionedBlockPathType
{
	//? if >=1.21 {
	public static final PathType WATER = PathType.WATER;
	//?} else {
	/*public static final BlockPathTypes WATER = BlockPathTypes.WATER;
	*///?}
}