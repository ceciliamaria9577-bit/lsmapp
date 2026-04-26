package com.wiwiiwiii.lsmapp.data.network

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApi {

    private val baseUrl = "https://apjsjxfsadophtajgefp.supabase.co"
    private val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFwanNqeGZzYWRvcGh0YWpnZWZwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzcxNzA4NTEsImV4cCI6MjA5Mjc0Njg1MX0.WxP2DHo5E8u-MS0fnFOKOdAjnMru2jDw0lGayG49Beo"

    suspend fun register(email: String, password: String): String {

        val response = HttpClientProvider.client.post("$baseUrl/auth/v1/signup") {

            headers {
                append("apikey", apiKey)
                append("Content-Type", "application/json")
            }

            setBody(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )
        }

        return response.body()
    }

    suspend fun login(email: String, password: String): String {

        val response = HttpClientProvider.client.post("$baseUrl/auth/v1/token?grant_type=password") {

            headers {
                append("apikey", apiKey)
                append("Content-Type", "application/json")
            }

            setBody(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )
        }

        return response.body()
    }
}