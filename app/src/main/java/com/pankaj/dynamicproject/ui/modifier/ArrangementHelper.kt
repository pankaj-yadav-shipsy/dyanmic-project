package com.pankaj.dynamicproject.ui.modifier

import androidx.compose.foundation.layout.Arrangement

fun createHorizontalArrangement(prop: Map<String, Any?>?): Arrangement.Horizontal {
    return when (prop?.get("horizontalArrangement") as String?) {
        "start" -> Arrangement.Start
        "center" -> Arrangement.Center
        "end" -> Arrangement.End
        else -> Arrangement.Start
    }
}

fun createVerticalArrangement(prop: Map<String, Any?>?): Arrangement.Vertical {
    return when (prop?.get("verticalArrangement") as String?) {
        "top" -> Arrangement.Top
        "bottom" -> Arrangement.Bottom
        "center" -> Arrangement.Center
        else -> Arrangement.Top
    }
}