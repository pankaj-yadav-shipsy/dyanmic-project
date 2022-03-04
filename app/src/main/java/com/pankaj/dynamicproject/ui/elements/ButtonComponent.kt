package com.pankaj.dynamicproject.ui.elements

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pankaj.dynamicproject.ui.action.ActionHandler
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.render.RenderManager

@Composable
fun ButtonComponent(config: Map<String, Any?>?, rowScope: RowScope?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val action = config?.get("action") as Map<String, Any?>?
    val child = config?.get("child") as List<Map<String, Any?>>
    Button(onClick = {
        ActionHandler.actionHandler(
            action?.get("action_type") as String? ?: "",
            action
        )
    }, modifier = createModifier(prop)) {
        RenderManager(child = child)
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonTest() {
    Button(onClick = {}) {
        Text(text = "Pankaj working")
    }
}