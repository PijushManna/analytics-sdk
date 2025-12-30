package com.analytics.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun scheduleAnalyticsWorker(context: Context) {
    val request = PeriodicWorkRequestBuilder<AnalyticsWorker>(
        15L, TimeUnit.MINUTES
    ).build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            "analytics_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
}
