plugins {
	`maven-publish`
	`version-catalog`
	kotlin("jvm") version "2.1.0"
	id("net.researchgate.release") version "3.0.2"
}

repositories {
	mavenLocal()
	mavenCentral()
}

catalog {
	versionCatalog {
		from(files("./gradle/libs.versions.toml"))
	}
}

tasks.getByName<Jar>("jar") {
	enabled = true
}

release {
	buildTasks.add("publish")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["versionCatalog"])
		}
	}

	repositories {
		maven {
			name = "githubPackages"
			url = uri("https://maven.pkg.github.com/bible-game/version")
			credentials {
				username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
				password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
			}
		}
	}
}

tasks.register("printVersion") {
	doLast {
		println(project.version)
	}
}