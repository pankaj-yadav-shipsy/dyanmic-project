package com.pankaj.dynamicproject.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pankaj.dynamicproject.R

fun Any?.convertToDp(): Dp {
    if (this != null)
        return (this as Number).toInt().dp
    return 0.dp
}

fun Any?.convertToSp(): TextUnit {
    if (this != null)
        return (this as Number).toInt().sp
    return TextUnit.Unspecified
}

fun Any?.convertToFontWeight(): FontWeight? {
    if (this != null)
        return FontWeight((this as Number).toInt())
    return null
}

fun Any?.convertToColor(): Color {
    if (this != null)
        return Color(android.graphics.Color.parseColor("#$this"))
    return Color.Unspecified
}

fun String?.getResourceId(): Int {
    if (this != null) {
        return when (this) {
            "call-icon" -> R.drawable.domino_call
            "navigation-icon" -> R.drawable.domino_navigate
            else -> 0
        }
    }
    return 0
}