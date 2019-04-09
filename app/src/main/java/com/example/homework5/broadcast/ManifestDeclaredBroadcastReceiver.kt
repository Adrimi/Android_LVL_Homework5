package com.example.homework5.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.homework5.R
import com.example.homework5.utilities.NotificationFactory
import com.example.homework5.utilities.logd
import java.lang.NullPointerException

class ManifestDeclaredBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        notificationFactory.show(
            context,
            context.getString(R.string.powiadomienie_1_title),
            context.getString(R.string.powiadomienie_1_message)
        )
    }

    private val notificationFactory by lazy { NotificationFactory() }
}