package com.pankaj.dynamicproject.ui.config

import android.content.Context
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import com.pankaj.dynamicproject.R


fun updateScreenTheme(
    context: Context,
    screenData: List<Map<String, Any?>?>
): List<Map<String, Any?>?> {
    val updatedList = arrayListOf<Map<String, Any?>?>()
    val theme = loadThemeFile(context)
    updatedList.addAll(screenData)
    return updateTheme(parent = updatedList, theme = theme)
}

fun updateTheme(
    parent: ArrayList<Map<String, Any?>?>,
    theme: Map<String, Any?>
): List<Map<String, Any?>?> {
    val tempParent = arrayListOf<Map<String, Any?>?>()
    tempParent.addAll(parent)
    for ((index, child) in parent.withIndex()) {
        var tempChild = mutableMapOf<String, Any?>()
        for ((key, value) in child!!) {
            tempChild[key] = value
        }
        if (tempChild.containsKey("child")) {
            tempChild["child"] =
                updateTheme(
                    parent = tempChild.get("child") as ArrayList<Map<String, Any?>?>,
                    theme
                )
        }
        if (tempChild.containsKey("theme")) {
            val obj = updateScreenConfig( getTheme(theme, child["theme"] as String),tempChild)
            for((key,value) in obj){
                tempChild[key] = value
            }
        }
        tempParent[index] = tempChild

    }
    return tempParent
}

fun updateScreenConfig(theme: Map<String, Any?>, screen: Map<String, Any?>): Map<String, Any?> {
    val screenObj = screen as LinkedHashMap<String, Any?>
    screenObj.remove("theme")
    for ((key, value) in theme) {
        screenObj[key] = value
    }
    return screenObj
}

fun getTheme(theme: Map<String, Any?>, name: String): Map<String, Any?> {
    return theme[name] as Map<String, Any?>
}

fun loadThemeFile(context: Context): Map<String, Any?> {
    val config = context.resources.openRawResource(R.raw.themer)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(
        config,
        object : TypeToken<Map<String, Any?>>() {}.type
    )
}