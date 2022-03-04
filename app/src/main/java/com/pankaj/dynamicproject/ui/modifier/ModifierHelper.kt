package com.pankaj.dynamicproject.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.pankaj.dynamicproject.ui.util.convertToColor
import com.pankaj.dynamicproject.ui.util.convertToDp


fun createModifier(config: Map<String, Any?>? = null, rowScope: RowScope? = null,columnScope: ColumnScope?=null ): Modifier {
    val prop = config?.get("modifier") as Map<String, Any?>?
    var modifier: Modifier? = null
    for ((key, value) in prop ?: mapOf()) {
        when (key) {
            "width" ->
                modifier = getModifierObj(modifier).width(value.convertToDp())
            "height" ->
                modifier = getModifierObj(modifier).height(value.convertToDp())
            "fillWidth" ->
                modifier = getModifierObj(modifier).fillMaxWidth()
            "fillHeight" ->
                modifier = getModifierObj(modifier).fillMaxHeight()
            "fill" ->
                modifier = getModifierObj(modifier).fillMaxSize()
            "size" ->
                modifier = getModifierObj(modifier).size(value.convertToDp())
            "clip" ->
                modifier = getModifierObj(modifier).clip(getShape(value as String))
            "background" -> {
                modifier = getModifierObj(modifier).background(value.convertToColor())
            }
            "padding" -> {
                modifier = if (value is Number) {
                    getModifierObj(modifier).padding(value.convertToDp())
                } else {
                    val (start, end, top, bottom) = getPadding(config = value as Map<String, Any?>)
                    getModifierObj(modifier).padding(
                        start = start,
                        top = top,
                        end = end,
                        bottom = bottom
                    )
                }
            }
            "border" -> {
                val (dp, color, shape) = getBorder(config = value as Map<String, Any?>)
                modifier = getModifierObj(modifier).border(dp, color, shape)
            }
            "weight" -> {
                rowScope?.apply {
                    modifier = getModifierObj(modifier).weight(1f)
                }
                columnScope?.apply {
                    modifier = getModifierObj(modifier).weight(1f)
                }

            }

        }
    }
    return modifier ?: Modifier
}

private fun getModifierObj(modifier: Modifier?): Modifier {
    return modifier ?: Modifier
}

private fun getBorder(config: Map<String, Any?>?): Border {
    return Border(
        config?.get("size").convertToDp(),
        config?.get("color").convertToColor(),
        getShape(config?.get("type") as String?)
    )
}

private data class Border(val dp: Dp, val brush: Color, val shape: Shape)
private data class Padding(val start: Dp, val end: Dp, val top: Dp, val bottom: Dp)


private fun getShape(type: String?): Shape {
    return when (type) {
        "circle" -> CircleShape
        else -> RectangleShape
    }
}

private fun getPadding(config: Map<String, Any?>?): Padding {
    return Padding(
        (config?.get("start") ?: 0).convertToDp(),
        (config?.get("end") ?: 0).convertToDp(),
        (config?.get("top") ?: 0).convertToDp(),
        (config?.get("bottom") ?: 0).convertToDp()
    )
}