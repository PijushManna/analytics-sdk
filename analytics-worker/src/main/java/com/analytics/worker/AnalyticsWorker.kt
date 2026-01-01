package com.analytics.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.analytics.core.EventTrackerFactory

internal class AnalyticsWorker(
    ctx: Context,
    params: WorkerParameters,
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            EventTrackerFactory.uploadNow()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
