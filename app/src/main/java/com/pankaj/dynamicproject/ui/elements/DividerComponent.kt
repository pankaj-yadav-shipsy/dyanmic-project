package com.pankaj.dynamicproject.ui.elements

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.util.convertToColor
import com.pankaj.dynamicproject.ui.util.convertToDp

@Composable
fun DividerComponent(config: Map<String, Any?>?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    Divider(
        modifier = createModifier(prop),
        color = prop?.get("color").convertToColor(),
        thickness = prop?.get("thickness").convertToDp()
    )
}