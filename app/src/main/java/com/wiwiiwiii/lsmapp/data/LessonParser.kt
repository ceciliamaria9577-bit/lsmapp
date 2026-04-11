package com.wiwiiwiii.lsmapp.data

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.wiwiiwiii.lsmapp.data.model.Lesson
import com.wiwiiwiii.lsmapp.ui.lesson.model.StepType
import com.google.gson.JsonDeserializer

fun loadJSONFromAsset(context: Context): String {
    return context.assets.open("lessons.json")
        .bufferedReader()
        .use { it.readText() }
}

fun parseLessons(context: Context): List<Lesson> {
    val json = loadJSONFromAsset(context)

    val gson = GsonBuilder()
        .registerTypeAdapter(StepType::class.java, JsonDeserializer { jsonElement, _, _ ->
            StepType.valueOf(jsonElement.asString)
        })
        .create()

    val type = object : TypeToken<List<Lesson>>() {}.type
    return gson.fromJson(json, type)
}