plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdk = Sdk.compileSdk
    buildToolsVersion = AppCoordinates.buildTools

    defaultConfig {
        applicationId = AppCoordinates.applicationId
        minSdk = Sdk.minSdk
        targetSdk = Sdk.targetSdk
        versionCode = AppCoordinates.versionCode
        versionName = AppCoordinates.versionName
        testInstrumentationRunner = Libs.testImplementationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", BuildFields.PRIVATE_KEY, Keys.privateKey)
            buildConfigField("String", BuildFields.PUBLIC_KEY, Keys.publicKey)
        }

        release {
            buildConfigField("String", BuildFields.PRIVATE_KEY, Keys.privateKey)
            buildConfigField("String", BuildFields.PUBLIC_KEY, Keys.publicKey)
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

    with(Libs.Androidx.Compose) {
        implementation(ui)
        implementation(material)
        implementation(uiTooling)
        androidTestImplementation(testing)
    }

    implementation(Libs.KotlinSerialization.serialization)

    implementation(Libs.Accompanist.systemUiController)

    implementation(Libs.Coil.coilCompose)

    implementation(Libs.NavigationCompose.navCompose)

    implementation(Libs.HiltNavigationCompose.hiltNavCompose)

    implementation(Libs.Material.material)

    implementation(Libs.Androidx.ActivityCompose.compose)

    implementation(Libs.Androidx.Core.core)

    implementation(Libs.Androidx.Lifecycle.lifecycle)

    implementation(Libs.Androidx.AppCompat.compat)

    androidTestImplementation(Libs.Androidx.Test.Junit.junit)
    androidTestImplementation(Libs.Androidx.Test.Espresso.espresso)


    testImplementation("junit:junit:4.+")
    implementation(kotlin("reflect"))
}