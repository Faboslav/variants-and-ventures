package com.faboslav.variantsandventures.common.versions;

import net.minecraft.nbt.CompoundTag;

//? >=1.21.6 {
import net.minecraft.world.level.storage.ValueInput;
//?}

public final class VersionedNbt
{
	//? if >=1.21.6 {
	public static int getInt(ValueInput nbt, String key, int defaultValue)
	{
		return nbt.getIntOr(key, defaultValue);
	}
	//?}

	public static int getInt(CompoundTag nbt, String key, int defaultValue)
	{
		//? >= 1.21.5 {
		return nbt.getIntOr(key, defaultValue);
		//?} else {
		/*return nbt.contains(key) ? nbt.getInt(key) : defaultValue;
		*///?}
	}

	//? >=1.21.6 {
	public static boolean getBoolean(ValueInput nbt, String key, boolean defaultValue)
	//?} else {
	/*public static boolean getBoolean(CompoundTag nbt, String key, boolean defaultValue)
	*///?}
	{
		//? >= 1.21.5 {
		return nbt.getBooleanOr(key, defaultValue);
		 //?} else {
		/*return nbt.contains(key) ? nbt.getBoolean(key) : defaultValue;
		*///?}
	}
}
