plugins {
	id("multiloader-common")
	id("fabric-loom")
	id("dev.kikugie.j52j") version "2.0"
	id("accessconverter")
}

loom {
	accessWidenerPath = common.project.file("../../src/main/resources/versions/${commonMod.mc}/${mod.id}.accesswidener")

	mixin {
		useLegacyMixinAp = false
	}
}

accessConverter {
	mcVersion(commonMod.mc)

	convertAW {
		this.sortInput(true)
		this.fileToConvert(common.project.file("../../src/main/resources/versions/${commonMod.mc}/${mod.id}.accesswidener"))
		this.fileOutput(common.project.file("../../src/main/resources/versions/${commonMod.mc}/accesstransformer.cfg"))
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
