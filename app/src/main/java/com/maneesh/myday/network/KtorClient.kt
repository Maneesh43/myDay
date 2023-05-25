package com.maneesh.myday.network


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.gson.gson

object KtorClient {

    private var client: HttpClient = HttpClient(CIO) {
        expectSuccess = false
        install(Logging)
        install(HttpCache)
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
    }


    suspend fun getSloka(host: String, path: String): Shloka? {

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