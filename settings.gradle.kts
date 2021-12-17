enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs"){
            from(files("libs.version.toml"))
        }
    }

}
rootProject.name = "Kundroid"
include(":app")
include(":buildplugin")
