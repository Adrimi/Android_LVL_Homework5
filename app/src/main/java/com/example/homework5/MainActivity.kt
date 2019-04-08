package com.example.homework5

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework5.broadcast.ManifestDeclaredBroadcastReceiver
import com.example.homework5.utilities.NotificationFactory
import com.example.homework5.utilities.fromAndroid
import java.util.*

class MainActivity : AppCompatActivity() {

    private val notificationFactory by lazy { NotificationFactory() }
    private val alarmManager: AlarmManager
        get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 36)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNotificationChannels()
        launchFamiliadaAlarm()
    }

    private fun launchFamiliadaAlarm() {
        Intent(this, ManifestDeclaredBroadcastReceiver::class.java)
            .apply { action = "FamiliadaZarazWiecDoTelewizora" }
            .let { PendingIntent.getBroadcast(this, 0, it, 0) }
            .let {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    it
                )
            }
    }

    private fun initNotificationChannels() {
        fromAndroid(Build.VERSION_CODES.O) {
            notificationFactory.createNotificationChannels(this)
        }
    }
}
