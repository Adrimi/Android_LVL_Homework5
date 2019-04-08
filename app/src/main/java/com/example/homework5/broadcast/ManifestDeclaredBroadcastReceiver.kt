package com.example.homework5.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.homework5.R
import com.example.homework5.utilities.NotificationFactory
import com.example.homework5.utilities.logd
import java.lang.NullPointerException

class ManifestDeclaredBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals("FamiliadaZarazWiecDoTelewizora")) {
            logd("powiadomienie Broadcast!")
        } else if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            notificationFactory.show(
                context,
                context.getString(R.string.powiadomienie_1_title),
                context.getString(R.string.powiadomienie_1_message)
            )
        }
        else if (intent.action == null) {
            throw NullPointerException("Intent's action is null!")
        }
    }

    private val notificationFactory by lazy { NotificationFactory() }
    private val TAG = "ManiBroadcastReceiver"
}