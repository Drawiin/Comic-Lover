// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots") {
            content {
                includeModule("com.google.dagger", "hilt-android-gradle-plugin")
            }
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        /*Fixme
            Hilt conflict with gradle:7.0.0-beta03 new names c
            hange when is corrected in release version
            https://github.com/google/dagger/issues/2631 */
        classpath("com.google.dagger:hilt-android-gradle-plugin:${"HEAD-SNAPSHOT"}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}