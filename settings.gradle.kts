pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		maven("https://maven.fabricmc.net/")
		maven("https://maven.architectury.dev/")
		maven("https://files.minecraftforge.net/maven/")
		maven("https://maven.kikugie.dev/releases")
		maven("https://maven.kikugie.dev/snapshots")
		maven("https://maven.parchmentmc.org")
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.6-beta.1"
}

rootProject.name = "variantsandventures"

val commonVersions = providers.gradleProperty("stonecutter_enabled_common_versions").orNull?.split(",")?.map { it.trim() } ?: emptyList()
val fabricVersions = providers.gradleProperty("stonecutter_enabled_fabric_versions").orNull?.split(",")?.map { it.trim() } ?: emptyList()
val neoforgeVersions = providers.gradleProperty("stonecutter_enabled_neoforge_versions").orNull?.split(",")?.map { it.trim() } ?: emptyList()
val dists = mapOf(
	"common" to commonVersions,
	//"fabric" to fabricVersions,
	//"neoforge" to neoforgeVersions
)
val uniqueVersions = dists.values.flatten().distinct()

stonecutter {
	create(rootProject) {
		versions(*uniqueVersions.toTypedArray())

		dists.forEach { (branchName, branchVersions) ->
			branch(branchName) {
				versions(*branchVersions.toTypedArray())
			}
		}
	}
}