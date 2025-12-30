val IS_CI = System.getenv("CI") == "true"

plugins {
	id("dev.kikugie.stonecutter")
	id("net.neoforged.moddev") version "2.0.115" apply false
	id("fabric-loom") version "1.14-SNAPSHOT" apply false
}

stonecutter {
	parameters {
		filters.exclude("**/*.accesswidener")

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
else stonecutter active "1.21.11" /* [SC] DO NOT EDIT */
