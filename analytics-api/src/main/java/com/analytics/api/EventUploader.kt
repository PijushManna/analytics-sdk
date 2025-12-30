package com.analytics.api

interface EventUploader {
    suspend fun upload(events: List<AnalyticsEvent>)
}