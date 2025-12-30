package com.analytics.api

interface EventStore {
    suspend fun save(event: AnalyticsEvent)
    suspend fun flush()
    suspend fun count(): Int
}