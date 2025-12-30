package com.analytics.core

import com.analytics.api.AnalyticsEvent
import com.analytics.api.AnalyticsProvider
import com.analytics.api.EventUploader

class ProviderRouter(
    private val providers: List<AnalyticsProvider>,
) : EventUploader {
    override suspend fun upload(events: List<AnalyticsEvent>) {
        events.forEach { event ->
            providers.forEach { it.track(event) }
        }
    }
}
