package com.pankaj.dynamicproject.ui.modifier

import androidx.compose.ui.Alignment

fun createHorizontalAlignment(prop: Map<String, Any?>?): Alignment.Horizontal {
    return when (prop?.get("horizontalAlignment") as String?) {
        "start" -> Alignment.Start
        "center" -> Alignment.CenterHorizontally
        "end" -> Alignment.End
        else -> Alignment.Start
    }
}

fun createVerticalAlignment(prop: Map<String, Any?>?): Alignment.Vertical {
    return when (prop?.get("verticalAlignment") as String?) {
        "top" -> Alignment.Top
        "bottom" -> Alignment.Bottom
        "center" -> Alignment.CenterVertically
        else -> Alignment.Top
    }
}

fun createAlignment(prop: Map<String, Any?>?): Alignment {
    return Alignment.Center
}