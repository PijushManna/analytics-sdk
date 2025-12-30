package com.analytics.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.analytics.api.EventStore

class AnalyticsWorker(
    ctx: Context,
    params: WorkerParameters,
    private val store: EventStore
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        store.flush()
        return Result.success()
    }
}
