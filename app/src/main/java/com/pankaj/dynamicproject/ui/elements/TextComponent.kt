package com.pankaj.dynamicproject.ui.elements

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.util.convertToColor
import com.pankaj.dynamicproject.ui.util.convertToFontWeight
import com.pankaj.dynamicproject.ui.util.convertToSp

@Composable
fun TextComponent(config: Map<String, Any?>?, rowScope: RowScope?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val data = config?.get("data") as String
    Text(
        text = data,
        modifier = createModifier(prop, rowScope = rowScope),
        color = prop?.get("color").convertToColor(),
        fontSize = prop?.get("font-size").convertToSp(),
        fontWeight = prop?.get("font-weight").convertToFontWeight()
    )
}
