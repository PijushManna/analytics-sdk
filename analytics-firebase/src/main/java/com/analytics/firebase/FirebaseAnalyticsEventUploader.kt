package com.analytics.firebase

import android.content.Context
import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventUploader
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsEventUploader(context: Context) : EventUploader {
    private val firebase = FirebaseAnalytics.getInstance(context)
    override suspend fun upload(events: List<AnalyticsEvent>) {
        events.forEach {
            firebase.logEvent(it.name, it.params.toBundle())
        }
    }
}