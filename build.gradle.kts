// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.Hilt.androidGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        with(Libs.Kotlin){
            classpath(gradlePlugin)
            classpath(serialization)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}