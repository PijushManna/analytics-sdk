package com.analytics.store.impl

import android.content.Context
import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventStore
import com.analytics.store.dao.EventDao
import com.analytics.store.db.AnalyticsDatabase
import com.analytics.store.mapper.toDomain
import com.analytics.store.mapper.toEntity

class RoomEventStore(
    private val context: Context,
) : EventStore {
    private val db = AnalyticsDatabase.create(context)
    private val dao: EventDao = db.eventDao

    override suspend fun save(event: AnalyticsEvent) {
        dao.insert(event.toEntity())
    }

    override suspend fun flush(count: Int): List<AnalyticsEvent> {
        val batch = dao.getBatch(count)
        dao.delete(batch.map { it.id })
        return batch.map { it.toDomain() }
    }

    override suspend fun count(): Int = dao.count()
}