dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
         // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "Comic Lover"
include(":app", ":core", ":network")
include(":common-ui")
include(":feature-main")
