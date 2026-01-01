package com.analytics.noop

import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventUploader

class NoOpEventUploader : EventUploader {
    override suspend fun upload(events: List<AnalyticsEvent>) {
        println("NoOpEventUploader: $events")
    }
}