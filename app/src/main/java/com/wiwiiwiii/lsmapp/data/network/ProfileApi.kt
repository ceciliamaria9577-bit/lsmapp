package com.wiwiiwiii.lsmapp.data.network

import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*

class ProfileApi {

    private val baseUrl = "https://apjsjxfsadophtajgefp.supabase.co"
    private val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFwanNqeGZzYWRvcGh0YWpnZWZwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzcxNzA4NTEsImV4cCI6MjA5Mjc0Njg1MX0.WxP2DHo5E8u-MS0fnFOKOdAjnMru2jDw0lGayG49Beo"

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

        val raw = response.body<String>()
        println("PROFILE RESPONSE: $raw")
        println("STATUS: ${response.status}")

        if (!response.status.isSuccess()) {
            throw Exception("Error al guardar perfil")
        }
    }
}