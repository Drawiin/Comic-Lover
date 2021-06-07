package com.example.comiclover.di

import android.util.Log
import com.example.comiclover.BuildConfig
import com.example.comiclover.core.constants.NETWORK_TIME_OUT
import com.example.comiclover.core.constants.PRIVATE_KEY_NAME
import com.example.comiclover.core.constants.PUBLIC_KEY_NAME
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
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Named(PRIVATE_KEY_NAME)
    @Provides
    fun providesPrivateKey(): String = BuildConfig.PRIVATE_KEY

    @Singleton
    @Named(PUBLIC_KEY_NAME)
    @Provides
    fun providesPublicKey(): String = BuildConfig.PUBLIC_KEY

    @Singleton
    @Provides
    fun providesJsonSerializer(): JsonSerializer = KotlinxSerializer(Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    })

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun providesLogger() = object : Logger {
        val format = Json {
            prettyPrint = true
            isLenient = true
            prettyPrintIndent = " "
        }

        override fun log(message: String) {
            if (with(message) { startsWith("{") or startsWith("[") })
                try {
                    val json = format.parseToJsonElement(message)
                    val string = format.encodeToString(json)
                    if (string.length >= 4000){
                        Log.v("App logger =>", "Json to large use print instead")
                    }
                    Log.v("App logger =>", format.encodeToString(json))
                } catch (m: Exception) {
                    Log.v("App logger =>", m.message.toString())
                }
            else
                Log.v("App logger =>", message)
        }
    }

    @Singleton
    @Provides
    fun providesHttpClient(
        jsonSerializer: JsonSerializer,
        appLogger: Logger
    ): HttpClient = HttpClient(Android) {
        engine {
            connectTimeout = NETWORK_TIME_OUT
            socketTimeout = NETWORK_TIME_OUT
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
            host = "gateway.marvel.com:443/v1/public"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }
}