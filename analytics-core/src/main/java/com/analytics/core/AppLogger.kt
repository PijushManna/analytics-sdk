package com.analytics.core

import com.analytics.api.AnalyticsConfig
import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventTracker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppLogger(private val eventTracker: EventTracker, private val config: AnalyticsConfig) {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun logAppNavigation(previousScreen: String, currentScreen: String){
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    "navigation", params = getDefaultParams().apply {
                        put("previous_screen", previousScreen)
                        put("current_screen", currentScreen)
                    }
                )
            )
        }
    }

    fun logAppOpen() {
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    "app_open", params = getDefaultParams()
                )
            )
        }
    }

    fun logViewClick(viewName: String){
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    "view_click", params = getDefaultParams().apply {
                        put("view_name", viewName)
                    }
                )
            )
        }
    }

    fun logScreenView(screenName: String){
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    "screen_view", params = getDefaultParams().apply {
                        put("screen_name", screenName)
                    }
                )
            )
        }
    }

    fun logException(exception: Exception){
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    "exception", params = getDefaultParams().apply {
                        put("exception", exception.message ?: "")
                    }
                )
            )
        }
    }

    fun logAppShare(msg: String){
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    "app_share", params = getDefaultParams().apply {
                        put("message", msg)
                    }
                )
            )
        }
    }


    fun logCustomEvent(eventName: String, params: HashMap<String, String>) {
        scope.launch {
            eventTracker.track(
                AnalyticsEvent(
                    eventName, params = params
                )
            )
        }
    }

    private fun getDefaultParams(): HashMap<String, String> {
        return hashMapOf(
            "app_version" to config.appVersion,
            "device_id" to config.deviceId,
            "user_id" to config.userId,
            "timestamp" to TimestampFormatter.format(System.currentTimeMillis())
        )
    }
}