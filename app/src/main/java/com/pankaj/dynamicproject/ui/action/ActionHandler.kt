package com.pankaj.dynamicproject.ui.action

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference
import java.util.*


object ActionHandler {
    private lateinit var context: WeakReference<Context>
    private lateinit var screenToRender: MutableLiveData<String>
    fun setUpActionHandler(context: Context, screenToRender: MutableLiveData<String>) {
        this.context = WeakReference(context)
        this.screenToRender = screenToRender
    }

    fun actionHandler(actionType: String, data: Map<String, Any?>? = null) {
        when (actionType) {
            "call_user" -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:0123456789")
                context.get()?.startActivity(intent)
            }
            "open_map" -> {
                val uri: String =
                    java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", 28.463099, 77.033712)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                context.get()?.startActivity(intent)
            }
            "navigation" -> {
                handleScreenNavigation(data?.get("navigation") as String, data)
            }
        }
    }

    private fun handleScreenNavigation(screenName: String?, data: Map<String, Any?>?) {
        when (screenName) {
            "deliver_screen" -> {
                screenToRender.postValue(screenName)
            }
            "accept_screen" -> {}
            "pickup_screen" -> {}
        }
    }
}