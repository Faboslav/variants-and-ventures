plugins {
	id("multiloader-common")
	id("fabric-loom")
	id("accessconverter")
	kotlin("jvm") version "2.2.0"
	id("com.google.devtools.ksp") version "2.2.0-2.0.2"
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.14"
}

loom {
	accessWidenerPath = common.project.file("../../src/main/resources/accesswideners/${commonMod.mc}-${mod.id}.accesswidener")

	mixin {
		useLegacyMixinAp = false
	}
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

accessConverter {
	mcVersion(commonMod.mc)

	convertAW {
		this.sortInput(true)
		this.fileToConvert(common.project.file("../../src/main/resources/accesswideners/${commonMod.mc}-${mod.id}.accesswidener"))
		this.fileOutput(common.project.file("../../src/main/resources/accesstransformers/${commonMod.mc}-accesstransformer.cfg"))
	}
}

dependencies {
	minecraft(group = "com.mojang", name = "minecraft", version = commonMod.mc)
	mappings(loom.layered {
		officialMojangMappings()
		commonMod.depOrNull("parchment")?.let { parchmentVersion ->
			parchment("org.parchmentmc.data:parchment-${commonMod.mc}:$parchmentVersion@zip")
		}
	})

    compileOnly("org.spongepowered:mixin:0.8.5")

    "io.github.llamalad7:mixinextras-common:0.3.5".let {
        compileOnly(it)
        annotationProcessor(it)
    }

	modCompileOnly("net.fabricmc:fabric-loader:${commonMod.dep("fabric-loader")}")
	modCompileOnly("com.teamresourceful.resourcefullib:resourcefullib-common-${commonMod.dep("resourceful-lib.mc")}:${commonMod.dep("resourceful-lib.lib")}")
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
