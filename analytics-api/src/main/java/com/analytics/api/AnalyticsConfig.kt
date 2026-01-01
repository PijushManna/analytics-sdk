package com.analytics.api

/**
 * @param [batchSize] default value = 10
 * @param [flushIntervalMs] default value = 15_000, Ignored if background upload is not enabled
 */
data class AnalyticsConfig(
    val batchSize: Int = 10,
    val flushIntervalMs: Long = 15_000,
    val isDebugMode: Boolean = false,
    val appVersion:String = "1.0.0",
    val deviceId: String = "",
    val userId: String = "",
)