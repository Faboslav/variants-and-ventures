plugins {
	id("dev.kikugie.stonecutter")
	id("dev.architectury.loom") version "1.9-SNAPSHOT" apply false
	id("architectury-plugin") version "3.4-SNAPSHOT" apply false
	id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

stonecutter active file("versions/current")

stonecutter registerChiseled tasks.register("chiseledBuild", stonecutter.chiseled) {
	group = "project"
	ofTask("build")
}