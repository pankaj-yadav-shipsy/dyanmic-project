package com.pankaj.dynamicproject.ui.layout

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.render.RenderManager

@Composable
fun ScrollableComponent(config: Map<String, Any?>?, columnScope: ColumnScope?=null) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val child = config?.get("child") as List<Map<String, Any?>>
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = createModifier(prop, columnScope = columnScope)
    ) {
        items(child) { data ->
            RenderManager(child = listOf(data))
        }
    }
}
