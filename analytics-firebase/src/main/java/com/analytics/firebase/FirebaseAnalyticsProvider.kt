package com.analytics.firebase

import android.content.Context
import android.os.Bundle
import com.analytics.api.AnalyticsEvent
import com.analytics.api.AnalyticsProvider
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsProvider(
    context: Context
) : AnalyticsProvider {

    override val id: String = "firebase"
    private val firebase = FirebaseAnalytics.getInstance(context)

    override fun track(event: AnalyticsEvent) {
        firebase.logEvent(event.name, event.params.toBundle())
    }
}

fun Map<String, String>.toBundle(): Bundle {
    val bundle = Bundle()
    forEach { (key, value) ->
        bundle.putString(key, value)
    }
    return bundle
}
