package com.pankaj.dynamicproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.pankaj.dynamicproject.ui.action.ActionHandler
import com.pankaj.dynamicproject.ui.config.getScreenRenderingConfig
import com.pankaj.dynamicproject.ui.render.RenderManager
import com.pankaj.dynamicproject.ui.theme.DynamicProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenToRender = MutableLiveData<String>()
        screenToRender.value =("order_detail_screen")
        setContent {
            DynamicProjectTheme {
                // A surface container using the 'background' color from the theme
                val screen = screenToRender.observeAsState()
                RenderManager(child = getScreenRenderingConfig(screen.value ?: ""))
            }
        }
        ActionHandler.setUpActionHandler(this, screenToRender)
    }
}

@Preview(showBackground = true)
@Composable
fun NameView() {
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Customer", modifier = Modifier.width(100.dp))
        Text(
            text = "Raghav Singh", modifier = Modifier
                .weight(1f)
                .padding()
        )
        Image(
            painterResource(id = R.drawable.domino_call),
            contentDescription = "pankaj",
            modifier = Modifier.padding(10.dp)
        )
    }
}