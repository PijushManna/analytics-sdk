package com.analytics.api

interface EventStore {
    suspend fun save(event: AnalyticsEvent)
    suspend fun flush(count:Int): List<AnalyticsEvent>
    suspend fun count():Int
}