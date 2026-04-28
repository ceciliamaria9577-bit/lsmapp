package com.wiwiiwiii.lsmapp.data.network

import io.ktor.client.request.*
import io.ktor.http.*

class ProfileApi {

    private val baseUrl = "https://apjsjxfsadophtajgefp.supabase.co"
    private val apiKey = "TU_API_KEY_AQUI"

    suspend fun createProfile(
        token: String,
        userId: String,
        username: String,
        avatar: String
    ) {

        val response = HttpClientProvider.client.post(
            "$baseUrl/rest/v1/profiles"
        ) {

            headers {
                append("apikey", apiKey)
                append("Authorization", "Bearer $token")
                append("Content-Type", "application/json")
                append("Prefer", "return=minimal")
            }

            setBody(
                listOf(
                    mapOf(
                        "id" to userId,
                        "username" to username,
                        "avatar" to avatar
                    )
                )
            )
        }

        if (!response.status.isSuccess()) {
            throw Exception("Error al guardar perfil")
        }
    }
}