package com.wiwiiwiii.lsmapp.data

import com.wiwiiwiii.lsmapp.data.network.AuthApi

class AuthRepository {

    private val api = AuthApi()

    suspend fun register(email: String, password: String) {
        api.register(email, password)
    }

    suspend fun login(email: String, password: String) {
        api.login(email, password)
    }
}