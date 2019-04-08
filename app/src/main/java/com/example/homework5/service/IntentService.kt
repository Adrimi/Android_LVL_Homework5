package com.example.homework5.service

import android.app.IntentService
import android.content.Intent
import com.example.homework5.utilities.logd

class IntentService: IntentService("Familiada") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent?.action.equals("FamiliadaZarazWiecDoTelewizora")) {
            logd("powiadomienie z godziny 16:20 działa")
        }
    }
}