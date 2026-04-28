package com.wiwiiwiii.lsmapp.data.network

import kotlinx.serialization.json.Json

object JsonProvider {

    val json = Json {
        ignoreUnknownKeys = true
    }
}