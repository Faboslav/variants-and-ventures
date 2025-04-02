plugins {
	id("java")
	id("idea")
	id("dev.architectury.loom")
	id("architectury-plugin")
}

val minecraftVersion = stonecutter.current.version

fun modrinth(name: String, version: String): String {
	return "maven.modrinth:$name:$version"
}

fun prop(name: String): String {
	val stonecutterProject = rootProject.project(stonecutter.current.project)
	return stonecutterProject.property(name).toString()
}

base {
	archivesName.set("${prop("mod_id")}-common-mc${minecraftVersion}-${prop("mod_version")}")
}

architectury {
	common(prop("enabled_platforms").split(","))
}

loom {
	silentMojangMappingsLicense()

	accessWidenerPath = getRootProject().file("common/src/main/resources/${prop("mod_id")}.accesswidener")

	runConfigs.all {
		ideConfigGenerated(true)
	}
}

dependencies {
	minecraft(group = "com.mojang", name = "minecraft", version = prop("minecraft_version"))

	mappings(loom.layered {
		officialMojangMappings()

		if (prop("parchment_version") != "") {
			parchment("org.parchmentmc.data:parchment-${prop("minecraft_version")}:${prop("parchment_version")}@zip")
		}
	})

	// General
	modImplementation("net.fabricmc:fabric-loader:${prop("fabric_loader_version")}")

	// Required client
	modImplementation(modrinth("yacl", "${prop("yacl_version")}-fabric"))
}

tasks {
	processResources {
		val expandProps = mapOf(
			"modJavaVersion" to prop("mod_java_version.version"),
			"modId" to prop("mod_id"),
			"modVersion" to prop("mod_version"),
			"minecraftVersion" to prop("minecraft_version"),
		).filterValues { it?.isNotEmpty() == true }.mapValues { (_, v) -> v!! }

		filesMatching(listOf("*.mixins.json")) {
			expand(expandProps)
		}

		inputs.properties(expandProps)
		duplicatesStrategy = DuplicatesStrategy.INCLUDE
	}
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.toVersion(prop("mod_java_version"))
	targetCompatibility = JavaVersion.toVersion(prop("mod_java_version"))
}

tasks.withType<JavaCompile>().configureEach {
	options.encoding = "UTF-8"
	options.release.set(prop("mod_java_version").toInt())
}

tasks.withType<Jar>().configureEach {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

repositories {
	// CurseForge
	maven {
		name = "CurseForge"
		url = uri("https://www.cursemaven.com")
	}

	// Modrinth
	maven {
		url = uri("https://api.modrinth.com/maven")
		content {
			includeGroup("maven.modrinth")
		}
	}

	// Mixin Extras
	maven {
		url = uri("https://jitpack.io")
		content {
			includeGroup("com.github.llamalad7")
		}
	}

	// YACL
	maven {
		url = uri("https://maven.isxander.dev/releases")
	}
	maven {
		url = uri("https://maven.isxander.dev/snapshots")
	}

	// Kotlin for Forge
	maven {
		url = uri("https://thedarkcolour.github.io/KotlinForForge/")
	}

	// Quilt
	maven {
		url = uri("https://maven.quiltmc.org/repository/release")
	}

	// Parchment
	maven {
		name = "ParchmentMC"
		url = uri("https://maven.parchmentmc.org")
	}
}