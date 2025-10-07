plugins {
	id("fabric-loom")
	`multiloader-loader`
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.22"
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

stonecutter {
	constants["modmenu"] = commonMod.depOrNull("modmenu") != null
}

dependencies {
	minecraft("com.mojang:minecraft:${commonMod.mc}")
	mappings(loom.layered {
		officialMojangMappings()
		commonMod.depOrNull("parchment")?.let { parchmentVersion ->
			parchment("org.parchmentmc.data:parchment-${commonMod.mc}:$parchmentVersion@zip")
		}
	})

	modImplementation("net.fabricmc:fabric-loader:${commonMod.dep("fabric-loader")}")
	modApi("net.fabricmc.fabric-api:fabric-api:${commonMod.dep("fabric-api")}+${commonMod.mc}")

	// Required dependencies
	modImplementation(
		"com.teamresourceful.resourcefullib:resourcefullib-fabric-${commonMod.dep("resourceful-lib.mc")}:${
			commonMod.dep(
				"resourceful-lib.lib"
			)
		}"
	)
	modImplementation("dev.isxander:yet-another-config-lib:${commonMod.dep("yacl")}-fabric")

	// Optional dependencies
	// Mod Menu (https://www.curseforge.com/minecraft/mc-mods/modmenu)
	commonMod.depOrNull("modmenu")?.let { modmenuVersion ->
		modImplementation("com.terraformersmc:modmenu:${modmenuVersion}")
	}

	commonMod.depOrNull("devauth")?.let { devauthVersion ->
		modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${devauthVersion}")
	}
}

loom {
	accessWidenerPath = common.project.file("../../src/main/resources/accesswideners/${commonMod.mc}-${mod.id}.accesswidener")

	runs {
		getByName("client") {
			client()
			configName = "Fabric Client"
			ideConfigGenerated(true)
		}
		getByName("server") {
			server()
			configName = "Fabric Server"
			ideConfigGenerated(true)
		}
	}

	mixin {
		defaultRefmapName = "${mod.id}.refmap.json"
	}
}

tasks.named<ProcessResources>("processResources") {
	exclude("accesswideners/**")

	val awFile = project(":common").file("src/main/resources/accesswideners/${commonMod.mc}-${mod.id}.accesswidener")

	if (awFile.exists()) {
		from(awFile.parentFile) {
			include(awFile.name)
			rename(awFile.name, "${mod.id}.accesswidener")
			into("")
		}
	}
}