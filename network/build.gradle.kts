plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdk = Sdk.compileSdk

    defaultConfig {
        minSdk = Sdk.minSdk
        targetSdk = Sdk.targetSdk
        testInstrumentationRunner = Libs.testImplementationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
    with(Libs.Hilt) {
        implementation(android)
        kapt(compiler)
    }
    with(Libs.Ktor) {
        implementation(android)
        implementation(clientLoggingJvm)
        implementation(serialization)
    }

    implementation(Libs.Androidx.Core.core)
    implementation(Libs.Androidx.AppCompat.compat)
    implementation(Libs.Material.material)
    testImplementation(Test.junit)

    androidTestImplementation(Libs.Androidx.Test.Junit.junit)
    androidTestImplementation(Libs.Androidx.Test.Espresso.espresso)
}