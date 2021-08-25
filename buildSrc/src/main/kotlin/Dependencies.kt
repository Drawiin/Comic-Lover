object Sdk {
    const val minSdk = 23
    const val targetSdk = 30
    const val compileSdk = 30
}

object Libs {
    const val testImplementationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"

    object Kotlin {
        private const val version = "1.5.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$version"
    }

    object Hilt {
        private const val version = "2.37"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }


    object Ktor {
        private const val version = "1.5.0"
        const val android = "io.ktor:ktor-client-android:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
        const val clientLoggingJvm = "io.ktor:ktor-client-logging-jvm:$version"
    }

    object NavigationCompose {
        private const val version = "2.4.0-alpha04"
        const val navCompose = "androidx.navigation:navigation-compose:$version"
    }

    object HiltNavigationCompose {
        private const val version = "1.0.0-alpha03"
        const val hiltNavCompose = "androidx.hilt:hilt-navigation-compose:$version"
    }

    object KotlinSerialization {
        private const val version = "1.0.1"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
    }

    object Androidx {
        object Core {
            const val core = "androidx.core:core-ktx:1.6.0"
        }

        object AppCompat {
            const val compat = "androidx.appcompat:appcompat:1.3.1"
        }

        object Lifecycle {
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        }

        object ActivityCompose {
            const val compose= "androidx.activity:activity-compose:1.3.0-beta01"

        }

        object Compose {
            const val version = "1.0.1"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val testing = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Test {
            object Junit {
                const val junit = "androidx.test.ext:junit:1.1.2"
            }

            object Espresso {
                const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

            }
        }

    }

    object Material {
        private const val version = "1.4.0"
        const val material = "com.google.android.material:material:$version"
    }


    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.3.2"
    }

    object Accompanist {
        private const val version = "0.16.1"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

}

object Test {
    const val junit = "junit:junit:4.13.2"
}