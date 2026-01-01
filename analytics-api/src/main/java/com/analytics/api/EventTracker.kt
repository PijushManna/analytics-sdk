package com.analytics.api

interface EventTracker {
    suspend fun track(event: AnalyticsEvent)
}