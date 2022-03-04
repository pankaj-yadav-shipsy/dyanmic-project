package com.pankaj.dynamicproject.ui.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import com.pankaj.dynamicproject.ui.modifier.createModifier

@Composable
fun SpaceComponent(config: Map<String, Any?>?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    Spacer(modifier = createModifier(prop))
}