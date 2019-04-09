package com.example.homework5.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.homework5.R
import com.example.homework5.utilities.NotificationFactory

private const val NOTIFICATION_ID = 200


class ForegroundService: Service() {

    private val notificationFactory by lazy { NotificationFactory() }

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_ID, notificationFactory.create(this, this.getString(R.string.powiadomienie_4_title), this.getString(R.string.powiadomienie_4_message)))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}