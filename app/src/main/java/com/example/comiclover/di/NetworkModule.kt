package com.example.comiclover.di

import android.net.sip.SipErrorCode.TIME_OUT
import android.util.Log
import com.example.comiclover.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesJsonSerializer() = KotlinxSerializer(Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    })

    @Singleton
    @Provides
    fun providesLogger() = object : Logger {
        override fun log(message: String) {
            Log.v("App logger =>", message)
        }
    }

    @Singleton
    @Provides
    fun providesHttpClient(jsonSerializer: JsonSerializer, appLogger: Logger) = HttpClient(Android) {
        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }

        install(JsonFeature) {
            serializer = jsonSerializer
        }

        install(Logging) {
            logger = appLogger
            level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}