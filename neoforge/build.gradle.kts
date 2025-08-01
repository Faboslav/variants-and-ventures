plugins {
	`multiloader-loader`
	id("net.neoforged.moddev")
	kotlin("jvm") version "2.2.0"
	id("com.google.devtools.ksp") version "2.2.0-2.0.2"
	id("dev.kikugie.fletching-table.neoforge") version "0.1.0-alpha.14"
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

neoForge {
	enable {
		version = commonMod.dep("neoforge")
	}
}

dependencies {
	// Required dependencies
	implementation(
		"com.teamresourceful.resourcefullib:resourcefullib-neoforge-${commonMod.dep("resourceful-lib.mc")}:${
			commonMod.dep(
				"resourceful-lib.lib"
			)
		}"
	)
	implementation("dev.isxander:yet-another-config-lib:${commonMod.dep("yacl")}-neoforge")
}

neoForge {
	val at = common.project.file("../../src/main/resources/accesstransformers/${commonMod.mc}-accesstransformer.cfg");

	if (at.exists()) {
		accessTransformers.from(at.absolutePath)
		validateAccessTransformers = false
	}

	runs {
		register("client") {
			client()
			ideName = "NeoForge Client (${project.path})"
		}
		register("server") {
			server()
			ideName = "NeoForge Server (${project.path})"
		}
	}

	parchment {
		commonMod.depOrNull("parchment")?.let {
			mappingsVersion = it
			minecraftVersion = commonMod.mc
		}
	}

	mods {
		register(commonMod.id) {
			sourceSet(sourceSets.main.get())
		}
	}
}

sourceSets.main {
	resources.srcDir("src/generated/resources")
}

tasks.named<ProcessResources>("processResources") {
	exclude("accesstransformers/**", "accesswideners/**")

	val awFile = project(":common").file("src/main/resources/accesstransformers/${commonMod.mc}-accesstransformer.cfg")

	if (awFile.exists()) {
		from(awFile.parentFile) {
			include(awFile.name)
			rename { "accesstransformer.cfg" }
			into("META-INF")
		}
	}
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(":neoforge:${commonMod.propOrNull("minecraft_version")}:stonecutterGenerate")
}
