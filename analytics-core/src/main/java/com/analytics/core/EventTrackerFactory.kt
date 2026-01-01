package com.analytics.core

import com.analytics.api.AnalyticsConfig
import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventStore
import com.analytics.api.EventTracker
import com.analytics.api.EventUploader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object EventTrackerFactory : EventTracker {
    private val uploads = mutableSetOf<EventUploader>()
    private val storages = mutableSetOf<EventStore>()
    private var config = AnalyticsConfig()

    fun addConfig(config: AnalyticsConfig) {
        this.config = config
    }

    fun addUploader(uploader: EventUploader) {
        uploads += uploader
    }

    fun addEventStore(store: EventStore) {
        storages.add(store)
    }

    override suspend fun track(event: AnalyticsEvent) {
        if (isDebugMode){
            storages.forEach { store ->
                 println("Save to ${store.javaClass.name} : $event")
            }
        }else {
            storages.forEach { store ->
                store.save(event)
            }
        }
    }

    suspend fun uploadNow() {
        if (isDebugMode.not()) {
            val events = storages.firstOrNull()?.flush(count = config.batchSize)
            if (events!= null){
                uploads.forEach { uploader ->
                    uploader.upload(events)
                }
            }
        }
    }

    fun uploadInBackground() {
        if (isDebugMode.not()){
            CoroutineScope(Dispatchers.IO).launch {
                while ((storages.firstOrNull()?.count() ?: 0) > 0) {
                    delay(config.flushIntervalMs)
                    val events = storages.firstOrNull()?.flush(count = config.batchSize)
                    if (events != null) {
                        uploads.forEach { uploader ->
                            uploader.upload(events)
                        }
                    }
                }
            }
        }
    }

    fun isDebugMode(enabled: Boolean) {
        config = config.copy(isDebugMode = enabled)
    }

    val isDebugMode: Boolean
        get() = config.isDebugMode
    val uploadInterval:Long
        get() = config.flushIntervalMs
}