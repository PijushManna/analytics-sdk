package com.analytics.store.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "analytics_events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val paramsJson: String,
    val timestamp: Long
)