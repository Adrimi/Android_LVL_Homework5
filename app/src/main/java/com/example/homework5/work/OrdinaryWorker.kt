package com.example.homework5.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.homework5.R
import com.example.homework5.utilities.NotificationFactory

class OrdinaryWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        notificationFactory.show(
            applicationContext,
            applicationContext.getString(R.string.powiadomienie_3_title),
            applicationContext.getString(R.string.powiadomienie_3_message)
        )
        return Result.success()
    }

    private val notificationFactory by lazy { NotificationFactory() }
}