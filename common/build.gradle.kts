plugins {
	id("multiloader-common")
	id("fabric-loom-compat")
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.23"
}

loom {
	accessWidenerPath = common.project.file("../../src/main/resources/accesswideners/${commonMod.mc}-${mod.id}.accesswidener")

	if (stonecutter.eval(commonMod.mc, "<=1.21.11")) {
		mixin {
			useLegacyMixinAp = false
		}
	}
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

dependencies {
	minecraft(group = "com.mojang", name = "minecraft", version = commonMod.mc)

	if (stonecutter.eval(commonMod.mc, "<=1.21.11")) {
		mappings(loom.layered {
			officialMojangMappings()
			commonMod.depOrNull("parchment")?.let { parchmentVersion ->
				parchment("org.parchmentmc.data:parchment-${commonMod.mc}:$parchmentVersion@zip")
			}
		})
	}

	modCompileOnly("net.fabricmc:fabric-loader:${commonMod.dep("fabric_loader")}")
	modCompileOnly("com.teamresourceful.resourcefullib:resourcefullib-common-${commonMod.dep("resourceful_lib.mc")}:${commonMod.dep("resourceful_lib.lib")}")
	modCompileOnly("dev.isxander:yet-another-config-lib:${commonMod.dep("yacl")}-fabric")
}

val commonJava: Configuration by configurations.creating {
	isCanBeResolved = false
	isCanBeConsumed = true
}

val commonResources: Configuration by configurations.creating {
	isCanBeResolved = false
	isCanBeConsumed = true
}

artifacts {
	afterEvaluate {
		val mainSourceSet = sourceSets.main.get()
		mainSourceSet.java.sourceDirectories.files.forEach {
			add(commonJava.name, it)
		}
		mainSourceSet.resources.sourceDirectories.files.forEach {
			add(commonResources.name, it)
		}
	}
}