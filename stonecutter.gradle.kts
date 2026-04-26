val IS_CI = System.getenv("CI") == "true"

plugins {
	id("dev.kikugie.stonecutter")
	id("net.neoforged.moddev") version "2.0.140" apply false
	id("net.fabricmc.fabric-loom") version "1.15-SNAPSHOT" apply false
	id("net.fabricmc.fabric-loom-remap") version "1.15-SNAPSHOT" apply false
}

stonecutter {
	parameters {
		filters.exclude("**/*.accesswidener")

		replacements.string(current.parsed >= "26.1") {
			replace("EntityModelLayerRegistry", "ModelLayerRegistry")
			replace("net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents", "net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents")
			replace("ItemGroupEvents.MODIFY_ENTRIES_ALL", "CreativeModeTabEvents.MODIFY_OUTPUT_ALL")
		}

		replacements.string(current.parsed >= "1.21.11") {
			replace("ResourceLocation", "Identifier")
			replace("net.minecraft.Util", "net.minecraft.util.Util")
			replace("net.minecraft.world.entity.monster.Zombie", "net.minecraft.world.entity.monster.zombie.Zombie")
			replace("net.minecraft.client.model.ZombieModel", "net.minecraft.client.model.monster.zombie.ZombieModel")
			replace("net.minecraft.world.entity.monster.Skeleton", "net.minecraft.world.entity.monster.skeleton.Skeleton")
			replace("net.minecraft.client.model.SkeletonModel", "net.minecraft.client.model.monster.skeleton.SkeletonModel")
			replace("net.minecraft.world.entity.animal.Turtle", "net.minecraft.world.entity.animal.turtle.Turtle")
			replace("net.minecraft.world.entity.animal.IronGolem", "net.minecraft.world.entity.animal.golem.IronGolem")
			replace("net.minecraft.world.entity.projectile.Arrow", "net.minecraft.world.entity.projectile.arrow.Arrow")
			replace("net.minecraft.world.entity.projectile.AbstractArrow", "net.minecraft.world.entity.projectile.arrow.AbstractArrow")
			replace("net.minecraft.world.entity.projectile.Snowball", "net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball")
			replace("net.minecraft.world.level.GameRules", "net.minecraft.world.level.gamerules.GameRules")
		}
	}
}

if (IS_CI) stonecutter active null
else stonecutter active "26.1.2" /* [SC] DO NOT EDIT */
