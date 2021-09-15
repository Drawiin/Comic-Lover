plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Androidx.Compose.version
    }
}

dependencies {
    implementation(project(mapOf("path" to ":common-ui")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":network")))

    implementation(kotlin("reflect"))


    with(Libs.Hilt) {
        implementation(android)
        kapt(compiler)
    }

    with(Libs.Ktor) {
        implementation(android)
        implementation(clientLoggingJvm)
        implementation(serialization)
    }

    with(Libs.Androidx.Compose) {
        implementation(ui)
        implementation(material)
        implementation(uiTooling)
        androidTestImplementation(testing)
    }

    implementation(Libs.NavigationCompose.navCompose)

    implementation(Libs.HiltNavigationCompose.hiltNavCompose)

    with(Libs.Accompanist) {
        implementation(systemUiController)
        implementation(placeholderMaterial)
        implementation(insets)
        implementation(navigationAnimation)
    }

    implementation(Libs.KotlinSerialization.serialization)

    implementation(Libs.Androidx.AppCompat.compat)
    implementation(Libs.Androidx.Lifecycle.lifecycle)

    implementation(Libs.Coil.coilCompose)

    implementation(Libs.Androidx.AppCompat.compat)
    implementation(Libs.Androidx.ActivityCompose.compose)
    implementation(Libs.Androidx.Core.core)
    implementation(Libs.Androidx.Lifecycle.lifecycle)

    implementation(Libs.Material.material)
    testImplementation(Test.junit)

    androidTestImplementation(Libs.Androidx.Test.Junit.junit)
    androidTestImplementation(Libs.Androidx.Test.Espresso.espresso)
}