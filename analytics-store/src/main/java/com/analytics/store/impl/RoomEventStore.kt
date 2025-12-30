package com.analytics.store.impl

import com.analytics.api.AnalyticsEvent
import com.analytics.api.EventStore
import com.analytics.api.EventUploader
import com.analytics.store.dao.EventDao
import com.analytics.store.mapper.toDomain
import com.analytics.store.mapper.toEntity

class RoomEventStore(
    private val dao: EventDao,
    private val uploader: EventUploader
) : EventStore {

    override suspend fun save(event: AnalyticsEvent) {
        dao.insert(event.toEntity())
    }

    override suspend fun flush() {
        val batch = dao.getBatch(20)
        uploader.upload(batch.map { it.toDomain() })
        dao.delete(batch.map { it.id })
    }

    override suspend fun count(): Int = dao.count()
}