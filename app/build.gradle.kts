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
        testInstrumentationRunner = Versions.testImplementationRunner
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))

    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

    implementation("io.ktor:ktor-client-android:${Versions.ktor}")
    implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}")
    implementation("io.ktor:ktor-client-logging-jvm:${Versions.ktor}")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}")

    implementation("androidx.navigation:navigation-compose:${Versions.navigationCompose}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}")

    implementation("io.coil-kt:coil-compose:${Versions.coil}")

    implementation("androidx.core:core-ktx:${Versions.androidx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    testImplementation("junit:junit:4.+")

    androidTestImplementation("androidx.test.ext:junit:${Versions.junit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    implementation(kotlin("reflect"))
}