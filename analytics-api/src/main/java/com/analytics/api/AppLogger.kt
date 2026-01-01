package com.analytics.api

interface AppLogger : EventTracker  {
    fun init(config: AnalyticsConfig)
    fun addUploader(uploader: EventUploader)
    fun addEventStore(store: EventStore)
    suspend fun uploadNow()
    fun uploadInBackground()
    fun isDebugMode(enabled: Boolean)
}