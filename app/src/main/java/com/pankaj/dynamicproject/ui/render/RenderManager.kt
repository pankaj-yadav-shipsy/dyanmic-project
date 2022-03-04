package com.pankaj.dynamicproject.ui.render

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import com.pankaj.dynamicproject.ui.elements.*
import com.pankaj.dynamicproject.ui.layout.ColumnComponent
import com.pankaj.dynamicproject.ui.layout.ListComponent
import com.pankaj.dynamicproject.ui.layout.RowComponent
import com.pankaj.dynamicproject.ui.layout.ScrollableComponent

@Composable
fun RenderManager(child: List<Map<String, Any?>?>?, rowScope: RowScope? = null,columnScope: ColumnScope?=null) {
    for (item in child ?: listOf()) {
        when ((item?.get("type") as String).split("-")[1]) {
            "layout" -> {
                GetLayout(config = item, columnScope = columnScope)
            }
            "view" -> {
                GetView(config = item, rowScope = rowScope)
            }
        }
    }

}


@Composable
fun GetLayout(config: Map<String, Any?>?,columnScope: ColumnScope?=null) {
    when ((config?.get("type") as String).split("-")[0]) {
        "row" -> {
            RowComponent(
                config = config
            )
        }
        "column" -> {
            ColumnComponent(
                config = config
            )
        }
        "list" -> {
            ListComponent(config = config)
        }
        "scrollable"->{
            ScrollableComponent(config = config,columnScope)
        }
    }
}


@Composable
fun GetView(config: Map<String, Any?>?, rowScope: RowScope? = null,columnScope: ColumnScope?=null) {
    when ((config?.get("type") as String).split("-")[0]) {
        "text" -> {
            TextComponent(config, rowScope)
        }
        "image" -> {
            ImageComponent(config)
        }
        "divider" -> {
            DividerComponent(config = config)
        }
        "button" ->{
            ButtonComponent(config = config, rowScope = rowScope)
        }
        "space"->{
            SpaceComponent(config = config)
        }
    }
}