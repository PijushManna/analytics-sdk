package com.analytics.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.analytics.core.EventTrackerFactory.uploadInterval
import java.util.concurrent.TimeUnit

fun ScheduleAnalyticsWorker(context: Context) {
    val request = PeriodicWorkRequestBuilder<AnalyticsWorker>(
        uploadInterval, TimeUnit.MINUTES
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "analytics_worker", ExistingPeriodicWorkPolicy.KEEP, request
        )
}