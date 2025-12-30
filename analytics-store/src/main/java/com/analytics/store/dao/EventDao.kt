package com.analytics.store.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.analytics.store.entities.EventEntity

@Dao
interface EventDao {
    @Insert
    suspend fun insert(event: EventEntity)

    @Query("SELECT * FROM analytics_events LIMIT :limit")
    suspend fun getBatch(limit: Int): List<EventEntity>

    @Query("DELETE FROM analytics_events WHERE id IN (:ids)")
    suspend fun delete(ids: List<Long>)

    @Query("SELECT COUNT(*) FROM analytics_events")
    suspend fun count(): Int
}