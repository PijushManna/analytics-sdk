package com.analytics.api

data class AnalyticsEvent(
    val name:String,
    val params: Map<String, String> = emptyMap(),
    val timestamp: Long = System.currentTimeMillis()
)