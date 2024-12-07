plugins {
	id("maven-publish")
	id("version-catalog")
}

repositories {
	mavenLocal()
	mavenCentral()
}

catalog {
	versionCatalog {
		from(files("./versions.toml"))
	}
}