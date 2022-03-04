package com.pankaj.dynamicproject.ui.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.pankaj.dynamicproject.ui.config.mergeDataToDisplayWithScreenConfig
import com.pankaj.dynamicproject.ui.render.RenderManager

@Composable
fun ListComponent(config: Map<String, Any?>?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val data = config?.get("data") as List<Map<String, Any?>>?
    val child = config?.get("list_item") as List<Map<String, Any?>>
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(count = data?.size ?: 0) { index ->
            RenderManager(
                child =
                mergeDataToDisplayWithScreenConfig(child, data?.get(index) ?: mapOf())
            )
        }
    }
}
