package com.faboslav.variantsandventures.common.versions;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;

//? if >= 26.2 {
import net.minecraft.world.entity.EntityTypes;
//?}

//? if >= 1.20.6 {
import net.minecraft.world.entity.monster.skeleton.Bogged;
//?}

//? if >= 1.21.11 {
import net.minecraft.world.entity.monster.skeleton.Parched;
//?}

public class VersionedEntityType
{
	//? if >= 26.2 {
	public static final EntityType<Zombie> ZOMBIE = EntityTypes.ZOMBIE;
	public static final EntityType<Husk> HUSK = EntityTypes.HUSK;
	public static final EntityType<Skeleton> SKELETON = EntityTypes.SKELETON;
	public static final EntityType<Stray> STRAY = EntityTypes.STRAY;
	public static final EntityType<Bogged> BOGGED = EntityTypes.BOGGED;
	public static final EntityType<Parched> PARCHED = EntityTypes.PARCHED;
	//?} else {
	/*public static final EntityType<Zombie> ZOMBIE = EntityType.ZOMBIE;
	public static final EntityType<Husk> HUSK = EntityType.HUSK;
	public static final EntityType<Skeleton> SKELETON = EntityType.SKELETON;
	public static final EntityType<Stray> STRAY = EntityType.STRAY;
	//? if >= 1.20.6 {
	public static final EntityType<Bogged> BOGGED = EntityType.BOGGED;
	//?}
	//? if >= 1.21.11 {
	public static final EntityType<Parched> PARCHED = EntityType.PARCHED;
	//?}
	*///?}
}
