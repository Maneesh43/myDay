package com.maneesh.myday.network


import com.fasterxml.jackson.databind.DeserializationFeature
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.jackson.jackson


object KtorClient {

    private var client: HttpClient = HttpClient(CIO) {
        expectSuccess = false
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL

        }
        install(HttpCache)
        install(ContentNegotiation) {
            jackson(){
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
            }
        }
    }

    suspend fun getShloka(host: String, path: String): Shloka? {

        return try {
            val response: Shloka = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    it.host = host
                    path(path)
                }

            }.body()
            response
        } catch (e: Exception) {
            null
        }
    }


    fun closeConnection() = client.close()

}