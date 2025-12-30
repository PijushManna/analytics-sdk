package com.analytics.bom.di

import android.content.Context
import com.analytics.api.AnalyticsProvider
import com.analytics.api.EventStore
import com.analytics.api.EventUploader
import com.analytics.api.Tracker
import com.analytics.core.AnalyticsConfig
import com.analytics.core.AnalyticsTracker
import com.analytics.core.ProviderRouter
import com.analytics.firebase.FirebaseAnalyticsProvider
import com.analytics.store.db.AnalyticsDatabase
import com.analytics.worker.scheduleAnalyticsWorker

class AnalyticsX{
    private lateinit var tracker: Tracker
    private val providers = mutableListOf<AnalyticsProvider>()
    private lateinit var uploader: EventUploader


    fun init(context: Context, providers: List<AnalyticsProvider>,store: EventStore, uploader: EventUploader) {
        val provider = ProviderRouter(
            providers = listOf(
                FirebaseAnalyticsProvider(context)
            )
        )

        val db = AnalyticsDatabase.create(context)

        val tracker = AnalyticsTracker(
            store = store,
            config = AnalyticsConfig(
                batchSize = 10,
            )
        )

        scheduleAnalyticsWorker(context)
    }
}