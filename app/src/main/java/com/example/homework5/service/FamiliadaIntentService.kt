package com.example.homework5.service

import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import com.example.homework5.R
import com.example.homework5.utilities.NotificationFactory
import com.example.homework5.utilities.logd

class FamiliadaIntentService: IntentService("FamiliadaIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        notificationFactory.show(
            this,
            this.getString(R.string.powiadomienie_2_title),
            this.getString(R.string.powiadomienie_2_message)
        )
        logd("powiadomienie z godziny 16:20 działa")
        Toast.makeText(this, intent?.action, Toast.LENGTH_SHORT).show()
    }

    private val notificationFactory by lazy { NotificationFactory() }
}