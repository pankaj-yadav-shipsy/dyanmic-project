import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pankaj.dynamicproject.R

lateinit var itemIds: Map<String, Any?>

fun convertTmpDataToDevData(context: Context, screenConfig: Map<String, Any?>): Map<String, Any?> {
    val coverData = loadCoverData(context)
    itemIds = loadItemIds(context)
    val coverUpdated = updateScreenWithCover(screenConfig, coverData)
    val views = coverUpdated["view"] as List<Map<String, Any?>?>
    val resume = updateChildId(views, screenConfig)
    val data = updateView(coverUpdated, resume)
    println("output -->$data")
    return data
}


fun loadItemIds(context: Context): Map<String, Any?> {
    val config = context.resources.openRawResource(R.raw.items_config)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(
        config,
        object : TypeToken<Map<String, Any?>>() {}.type
    )
}

fun updateView(
    coverUpdated: Map<String, Any?>,
    resume: List<Map<String, Any?>?>
): Map<String, Any?> {
    val response = mutableMapOf<String, Any?>()
    for ((key, value) in coverUpdated) {
        response[key] = value
    }
    response["view"] = resume
    return response
}


fun loadCoverData(context: Context): Map<String, Any?> {
    val config = context.resources.openRawResource(R.raw.cover_manager)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(
        config,
        object : TypeToken<Map<String, Any?>>() {}.type
    )
}

fun updateScreenWithCover(
    screenConfig: Map<String, Any?>,
    coverData: Map<String, Any?>
): Map<String, Any?> {
    val devData = mutableMapOf<String, Any?>()
    if (screenConfig.containsKey("cover")) {
        val coverForScreen = coverData[screenConfig["cover"]] as Map<String, Any?>
        for ((key, value) in coverForScreen) {
            devData[key] = value
        }
    }
    return devData
}

fun updateChildId(
    views: List<Map<String, Any?>?>,
    screenConfig: Map<String, Any?>
): List<Map<String, Any?>?> {
    val tempParent = arrayListOf<Map<String, Any?>?>()
    tempParent.addAll(views)
    for ((index, child) in views.withIndex()) {
        var tempChild = mutableMapOf<String, Any?>()
        for ((key, value) in child!!) {
            tempChild[key] = value
        }
        if (tempChild.containsKey("child")) {
            tempChild["child"] =
                updateChildId(
                    views = tempChild.get("child") as ArrayList<Map<String, Any?>?>,
                    screenConfig
                )
        }
        if (tempChild.containsKey("child_id")) {
            val obj =
                updateScreenChild(getChild(screenConfig, child["child_id"] as String), tempChild)
            for ((key, value) in obj) {
                tempChild[key] = value
            }
        }
        tempParent[index] = tempChild

    }
    return tempParent
}

fun updateScreenChild(
    theme: List<Map<String, Any?>>,
    screen: Map<String, Any?>
): Map<String, Any?> {
    val tempList = mutableListOf<Map<String, Any?>>()
    tempList.addAll(theme)
    for ((index, child) in theme.withIndex()) {
        tempList[index] = updateItemIds(child)
    }
    val update = (screen as LinkedHashMap<String, Any?>).apply {
        remove("child_id")
        screen["child"] = tempList
    }
    return update
}

fun updateItemIds(screenConfig: Map<String, Any?>): Map<String, Any?> {
    var tempRes = screenConfig
    Log.d("pannkajworking", "updateItemIds: $screenConfig")
    if (screenConfig.containsKey("item_id")) {
        tempRes = itemIds[screenConfig["item_id"]] as Map<String, Any?>
    }
    return tempRes
}

fun getChild(theme: Map<String, Any?>, name: String): List<Map<String, Any?>> {
    return theme[name] as List<Map<String, Any?>>
}
