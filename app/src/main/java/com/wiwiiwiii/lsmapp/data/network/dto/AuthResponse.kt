package com.wiwiiwiii.lsmapp.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val access_token: String? = null,
    val refresh_token: String? = null,
    val user: User? = null,
    val error_description: String? = null
)

@Serializable
data class User(
    val id: String? = null,
    val email: String? = null
)