package com.analytics.store.mapper

import com.analytics.api.AnalyticsEvent
import com.analytics.store.entities.EventEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val gson = Gson()

internal fun EventEntity.toDomain(): AnalyticsEvent {
    val type = object : TypeToken<Map<String, String>>() {}.type
    return AnalyticsEvent(
        name = name,
        params = gson.fromJson(paramsJson, type),
        timestamp = timestamp
    )
}

internal fun AnalyticsEvent.toEntity(): EventEntity =
    EventEntity(
        name = name,
        paramsJson = gson.toJson(params),
        timestamp = timestamp
    )
