package com.wiwiiwiii.lsmapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wiwiiwiii.lsmapp.data.model.Lesson

fun loadJSONFromAsset(context: Context): String {
    return context.assets.open("lessons.json")
        .bufferedReader()
        .use { it.readText() }
}

fun parseLessons(context: Context): List<Lesson> {
    val json = loadJSONFromAsset(context)
    val type = object : TypeToken<List<Lesson>>() {}.type
    return Gson().fromJson(json, type)
}