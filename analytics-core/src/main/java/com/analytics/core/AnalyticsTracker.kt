package com.analytics.core

import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventStore
import com.analytics.api.Tracker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AnalyticsTracker(
    private val store: EventStore,
    private val config: AnalyticsConfig
) : Tracker{
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

     override fun track(event: AnalyticsEvent) {
        scope.launch {
            store.save(event)
            if (store.count() >= config.batchSize) {
                store.flush()
            }
        }
    }
}
