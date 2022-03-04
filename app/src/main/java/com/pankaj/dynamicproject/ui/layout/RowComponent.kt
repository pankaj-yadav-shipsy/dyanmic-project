package com.pankaj.dynamicproject.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.pankaj.dynamicproject.ui.modifier.createHorizontalArrangement
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.modifier.createVerticalAlignment
import com.pankaj.dynamicproject.ui.render.RenderManager

@Composable
fun RowComponent(config: Map<String, Any?>?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val child = config?.get("child") as List<Map<String, Any?>?>?
    Row(
        modifier = createModifier(prop),
        horizontalArrangement = createHorizontalArrangement(prop),
        verticalAlignment = createVerticalAlignment(prop)
    ) {
        RenderManager(child = child, rowScope = this)
    }
}