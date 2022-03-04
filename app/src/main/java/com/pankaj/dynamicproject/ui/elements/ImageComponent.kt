package com.pankaj.dynamicproject.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.pankaj.dynamicproject.R
import com.pankaj.dynamicproject.ui.modifier.createAlignment
import com.pankaj.dynamicproject.ui.modifier.createModifier
import com.pankaj.dynamicproject.ui.util.getResourceId


@Composable
fun ImageComponent(config: Map<String, Any?>?) {
    val prop = config?.get("prop") as Map<String, Any?>?
    val data = config?.get("data") as Map<String, Any?>?
    when ("painter") {
        "vector" -> VectorImageComponent(prop = prop, data = data)
        "painter" -> PainterImageComponent(prop = prop, data = data)
        "bitmap" -> BitmapImageComponent(prop = prop, data = data)
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