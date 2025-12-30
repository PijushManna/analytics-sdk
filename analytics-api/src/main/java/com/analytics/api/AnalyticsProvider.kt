package com.analytics.api

interface AnalyticsProvider {
    val id: String
    fun track(event: AnalyticsEvent)
}