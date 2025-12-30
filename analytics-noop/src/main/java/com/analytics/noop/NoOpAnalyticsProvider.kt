package com.analytics.noop

import com.analytics.api.AnalyticsEvent
import com.analytics.api.AnalyticsProvider

class NoOpAnalyticsProvider : AnalyticsProvider {
    override val id: String = "noop"
    override fun track(event: AnalyticsEvent) = Unit
}
