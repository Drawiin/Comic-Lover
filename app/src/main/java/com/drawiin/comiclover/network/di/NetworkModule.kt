package com.drawiin.comiclover.network.di

import android.util.Log
import com.drawiin.comiclover.BuildConfig
import com.drawiin.core.constants.APP_LOGGER_NAME
import com.drawiin.core.constants.NETWORK_TIME_OUT
import com.drawiin.comiclover.network.util.extensions.isValidJson
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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
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
            val maxAndroidLoggerLength = 4000
            if (message.isValidJson())
                try {
                    val json = format.parseToJsonElement(message)
                    val string = format.encodeToString(json)
                    Log.v(APP_LOGGER_NAME, string)
                    if (string.length >= maxAndroidLoggerLength)
                        Log.v(APP_LOGGER_NAME, "Json to large use print instead")
                } catch (e: Exception) {
                    Log.v(APP_LOGGER_NAME, e.message.toString())
                }
            else
                Log.v(APP_LOGGER_NAME, message)
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
            acceptContentTypes = listOf(ContentType.Application.Json, ContentType.Text.Plain)
            serializer = jsonSerializer
        }

        install(Logging) {
            logger = appLogger
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            host = "raw.githubusercontent.com/Lorenalgm/marvel-heroes/master"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }
}