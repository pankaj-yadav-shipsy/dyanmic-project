package com.pankaj.dynamicproject.ui.config

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pankaj.dynamicproject.R
import convertTmpDataToDevData

fun Context.getScreenRenderingConfig(screenTag: String): List<Map<String, Any?>?> {
    var screenConfig = getScreenConfig(this,screenTag)
    screenConfig = convertTmpDataToDevData(this,screenConfig)
    val (screenData, inputData) = fetchScreenRenderConfig(
        screenData = screenConfig
    )
    val renderConfig = updateScreenTheme(this,screenData)
    val data = mergeDataToDisplayWithScreenConfig(
        renderConfig = renderConfig,
        dataToDisplay = inputData
    )

    println("final data i got ${data}")
    return data

}

private fun getScreenConfig(context: Context,screenTag: String): Map<String, Any?> {
    val config = context.resources.openRawResource(R.raw.tpm_config)
        .bufferedReader().use { it.readText() }
    val wholeConfig: Map<String, Any?> = Gson().fromJson(
        config,
        object : TypeToken<Map<String, Any?>>() {}.type
    )
    return wholeConfig[screenTag] as Map<String, Any?>
}

private fun fetchScreenRenderConfig(screenData: Map<String, Any?>): Pair<List<Map<String, Any?>?>, Map<String, Any?>?> {
    return Pair(
        screenData["view"] as List<Map<String, Any?>?>,
        screenData["input"] as Map<String, Any?>?
    )
}

fun mergeDataToDisplayWithScreenConfig(
    renderConfig: List<Map<String, Any?>?>,
    dataToDisplay: Map<String, Any?>?
): List<Map<String, Any?>?> {
    val childReturn = updateChild(renderConfig, dataToDisplay)
    println("merge data $childReturn")
    return childReturn
}

fun updateChild(parent: List<Map<String, Any?>?>, dataToDisplay: Any?): List<Map<String, Any?>?> {
    for (child in parent) {
        if (child?.containsKey("child") == true) {
            updateChild(
                parent = child.get("child") as List<Map<String, Any?>>,
                getData(child, dataToDisplay = dataToDisplay)
            )
        } else {
            if (child?.containsKey("input") == true && dataToDisplay != null) {
                (child as LinkedHashMap<String, Any>)["data"] =
                    getData(child, dataToDisplay = dataToDisplay)
            }
        }
    }
    println("inside update $parent")
    return parent
}

fun getData(
    parent: Map<String, Any?>,
    dataToDisplay: Any?
): Any {
    return if (parent.containsKey("input")) {
        convertDataAndFindValue(
            dataToDisplay = (dataToDisplay as Map<String, Any?>)[parent["input"] as String],
            parent["dataType"] as String
        )
    } else
        dataToDisplay ?: ""
}


fun convertDataAndFindValue(dataToDisplay: Any?, dataType: String): Any {
    return when (dataType) {
        "list" -> {
            dataToDisplay as List<Map<String, Any?>>
        }
        "string" -> dataToDisplay as String
        "map" -> dataToDisplay as Map<String,Any>
        else -> ""
    }
}