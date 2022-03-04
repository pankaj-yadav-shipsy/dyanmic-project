package com.pankaj.dynamicproject.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.pankaj.dynamicproject.ui.modifier.createHorizontalAlignment
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.modifier.createVerticalArrangement
import com.pankaj.dynamicproject.ui.render.RenderManager

@Composable
fun ColumnComponent(config: Map<String, Any?>?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val child = config?.get("child") as List<Map<String, Any?>?>?
    Column(
        modifier = createModifier(prop),
        verticalArrangement = createVerticalArrangement(prop),
        horizontalAlignment = createHorizontalAlignment(prop)
    ) {
        RenderManager(child = child, columnScope = this)
    }
}