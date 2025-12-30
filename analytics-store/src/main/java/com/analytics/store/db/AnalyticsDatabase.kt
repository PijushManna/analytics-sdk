package com.analytics.store.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.analytics.store.dao.EventDao
import com.analytics.store.entities.EventEntity

@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AnalyticsDatabase : RoomDatabase() {

    abstract val eventDao: EventDao

    companion object {

        private const val DB_NAME = "analytics_db"

        @Volatile
        private var INSTANCE: AnalyticsDatabase? = null

        fun create(context: Context): AnalyticsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AnalyticsDatabase::class.java,
                    DB_NAME
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
