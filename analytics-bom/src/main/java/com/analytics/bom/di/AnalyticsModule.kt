package com.analytics.bom.di

import com.analytics.api.AnalyticsProvider
import com.analytics.api.EventStore
import com.analytics.api.EventUploader
import com.analytics.core.AnalyticsTracker
import com.analytics.core.ProviderRouter
import com.analytics.store.impl.RoomEventStore
import com.analytics.worker.AnalyticsWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val analyticsModule = module {
    lateinit var analyticsProvider: List<AnalyticsProvider>
    single { AnalyticsTracker(get(), get()) }
    single { get<AnalyticsTracker>().track(get())  }
    single<EventStore> { RoomEventStore(get(), get()) }
    single<EventUploader> { ProviderRouter(analyticsProvider) }

    worker {
        AnalyticsWorker(
            ctx = get(),
            params = get(),
            store = get()
        )
    }
}