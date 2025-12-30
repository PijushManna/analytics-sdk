package com.analytics.api

interface Tracker {
    fun track(event: AnalyticsEvent)
}