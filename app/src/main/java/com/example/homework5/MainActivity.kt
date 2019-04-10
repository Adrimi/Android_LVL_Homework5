package com.example.homework5

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.*
import com.example.homework5.broadcast.ManifestDeclaredBroadcastReceiver
import com.example.homework5.service.FamiliadaIntentService
import com.example.homework5.service.ForegroundService
import com.example.homework5.utilities.NotificationFactory
import com.example.homework5.utilities.fromAndroid
import com.example.homework5.work.OrdinaryWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val notificationFactory by lazy { NotificationFactory() }
    private val alarmManager: AlarmManager
        get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 9)
        set(Calendar.MINUTE, 26)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foregroundStopButton.setOnClickListener { stopForegroundProcess() }
        initNotificationChannels()
        launchNotify2()
        launchNotify3()
        launchNotify4()
    }

    private fun launchNotify2() {
        Intent(this, FamiliadaIntentService::class.java)
            .apply { action = "com.example.homework5.NOTIFY" }
            .let { PendingIntent.getService(this, 0, it, 0) }
            .let {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    it
                )
            }
        // Artificial lalunching service, to test that it's working.
        // Unfortunately, it don't want to launch at exact time :/
//        startService(Intent(this, FamiliadaIntentService::class.java))
    }

    private fun launchNotify3() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
        val request = OneTimeWorkRequest.Builder(OrdinaryWorker::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance()
            .enqueueUniqueWork(OrdinaryWorker::javaClass.name, ExistingWorkPolicy.APPEND,request)
    }

    private fun launchNotify4() {
        // start Foreground
        ContextCompat.startForegroundService(this, Intent(this, ForegroundService::class.java))
    }


    private fun stopForegroundProcess() {
        // stop foreground
        stopService(Intent(this, ForegroundService::class.java))
    }

    private fun initNotificationChannels() {
        fromAndroid(Build.VERSION_CODES.O) {
            notificationFactory.createNotificationChannels(this)
        }
    }
}
