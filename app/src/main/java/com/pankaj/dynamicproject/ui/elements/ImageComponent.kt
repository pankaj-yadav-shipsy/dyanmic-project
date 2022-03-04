package com.pankaj.dynamicproject.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.pankaj.dynamicproject.R
import com.pankaj.dynamicproject.ui.modifier.createAlignment
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.util.getResourceId


@Composable
fun ImageComponent(config: Map<String, Any?>?) {
    var prop = config?.get("prop") as Map<String, Any?>?
    val data = config?.get("data") as Map<String, Any?>?
    val action = config?.get("action") as Map<String, Any?>?
    val updateProp = mutableMapOf<String, Any?>()

    if (prop != null) {
        for ((key, value) in prop) {
            updateProp[key] = value
        }
        updateProp["action"] = action
    }
    when ("painter") {
        "vector" -> VectorImageComponent(prop = updateProp, data = data)
        "painter" -> PainterImageComponent(prop = updateProp, data = data)
        "bitmap" -> BitmapImageComponent(prop = updateProp, data = data)
    }
}

@Composable
fun VectorImageComponent(prop: Map<String, Any?>?, data: Map<String, Any?>?) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.domino_call),
        contentDescription = getContentDescription(data),
        modifier = createModifier(config = prop),
        alignment = createAlignment(prop),
        contentScale = ContentScale.Fit,
        alpha = DefaultAlpha,
        colorFilter = null
    )
}


@Composable
fun PainterImageComponent(prop: Map<String, Any?>?, data: Map<String, Any?>?) {
    Image(
        painterResource(id = (data?.get("image") as String?).getResourceId()),
        contentDescription = getContentDescription(data),
        modifier = createModifier(prop),
        alignment = createAlignment(prop),
        contentScale = ContentScale.Fit,
        alpha = DefaultAlpha,
        colorFilter = null
    )
}

@Composable
fun BitmapImageComponent(prop: Map<String, Any?>?, data: Map<String, Any?>?) {
    Image(
        painterResource(id = (data?.get("image") as String?).getResourceId()),
        contentDescription = getContentDescription(data),
        modifier = createModifier(prop),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit,
        alpha = DefaultAlpha,
        colorFilter = null
    )
}


fun getContentDescription(data: Map<String, Any?>?): String {
    return data?.get("description") as String
}