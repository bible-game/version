plugins {
	`version-catalog`
	`maven-publish`
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

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["versionCatalog"])
		}
	}
}