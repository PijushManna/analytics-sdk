# Analytics SDK Library

Latest Version 
[![Release](https://jitpack.io/v/pijushmanna/analytics-sdk.svg)](https://jitpack.io/#pijushmanna/analytics-sdk)


A **modular, extensible analytics SDK for Android** that supports event tracking, local persistence, background uploads, and pluggable analytics providers (Firebase and custom providers).

Built with **Kotlin**, **Room**, **WorkManager**

## ✨ Features

- 📦 Modular architecture (`api`, `core`, `store`, `worker`)
- 🗂 Local event storage using Room
- 🔁 Batch-based event uploading
- 🔌 Pluggable analytics providers
- 🧵 Background processing with workers
- 💉 First-class Dagger / Hilt support
- 🚀 Distributed via **JitPack**

## 📦 Modules

| Module | Description |
|------|------------|
| `analytics-api` | Public interfaces & data models |
| `analytics-core` | Core tracking & orchestration logic |
| `analytics-store` | Local persistence & event queue |
| `analytics-worker` | Background upload workers |
| `analytics-firebase` | Firebase Analytics provider |

---

## 🔖 Latest Version

[![Release](https://jitpack.io/v/pijushmanna/analytics-sdk.svg)](https://jitpack.io/#pijushmanna/analytics-sdk)

---

## 🛠 Setup

### - Add JitPack Repository

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```
### - Add versions in Version Catelog 
```shell
[versions]
analyticsSdk = "1.0.0"

[libraries]
analytics-api = { module = "com.github.pijushmanna.analytics-sdk:analytics-api", version.ref = "analyticsSdk" }
analytics-core = { module = "com.github.pijushmanna.analytics-sdk:analytics-core", version.ref = "analyticsSdk" }
analytics-store = { module = "com.github.pijushmanna.analytics-sdk:analytics-store", version.ref = "analyticsSdk" }
analytics-worker = { module = "com.github.pijushmanna.analytics-sdk:analytics-worker", version.ref = "analyticsSdk" }
analytics-firebase = { module = "com.github.pijushmanna.analytics-sdk:analytics-firebase", version.ref = "analyticsSdk" }
```
### - Add Dependencies

```groovy
implementation(libs.analytics.api)
implementation(libs.analytics.core)
implementation(libs.analytics.store)
implementation(libs.analytics.worker)
implementation(libs.analytics.firebase)
```

### - Dependency Injection - ***Dagger***

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    fun getAnalyticsConfig(): AnalyticsConfig =
        AnalyticsConfig(batchSize = 10)

    @Provides
    @Singleton
    fun getAnalyticsUploader(
        providers: Set<@JvmSuppressWildcards AnalyticsProvider>
    ): EventUploader =
        ProviderRouter(providers.toList())

    @Provides
    @Singleton
    fun getAnalyticsDb(
        @ApplicationContext context: Context
    ): AnalyticsDatabase =
        AnalyticsDatabase.create(context)

    @Provides
    @Singleton
    fun getAnalyticsStore(
        db: AnalyticsDatabase,
        uploader: EventUploader
    ): EventStore =
        RoomEventStore(
            dao = db.eventDao,
            uploader = uploader
        )

    @Provides
    @Singleton
    fun getAnalyticsTracker(
        store: EventStore,
        config: AnalyticsConfig
    ): AnalyticsTracker =
        AnalyticsTracker(
            store = store,
            config = config
        )
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsProviderBindings {

    @Binds
    @IntoSet
    abstract fun bindFirebaseAnalyticsProvider(
        provider: FirebaseAnalyticsProvider
    ): AnalyticsProvider
}
```
## Usage 
**Inject `AnalyticsTracker`**

```kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val analyticsTracker: AnalyticsTracker
) : ViewModel() {

    fun onButtonClicked() {
        analyticsTracker.track(
            name = "button_click",
            properties = mapOf(
                "screen" to "home"
            )
        )
    }
}
```
## 🔌 Adding a Custom Analytics Provider
```kotlin
class CustomAnalyticsProvider @Inject constructor() : AnalyticsProvider {

    override fun track(event: AnalyticsEvent) {
        // Send event to your backend
    }
}
```
Bind It
```kotlin
@Binds
@IntoSet
abstract fun bindCustomAnalyticsProvider(
    provider: CustomAnalyticsProvider
): AnalyticsProvider
```

## ⚙️ Configuration

The Analytics SDK can be configured using `AnalyticsConfig`.  
This allows you to control batching behavior and upload frequency.

### Example

```kotlin
AnalyticsConfig(
    batchSize = 10,
    flushIntervalMillis = 15_000
)
```

### Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `batchSize` | `Int` | Number of analytics events grouped and uploaded in a single batch |
| `flushIntervalMillis` | `Long` | Time interval (in milliseconds) between consecutive upload attempts |

### Notes

- A **smaller `batchSize`** results in more frequent network calls but lower memory usage.
- A **larger `batchSize`** improves network efficiency but may delay event uploads.
- Adjust `flushIntervalMillis` based on how often analytics data should be flushed.
- Recommended to fine-tune these values according to app usage patterns and network conditions.

## 🧪 Best Practices

- ✅ Use **stable, tagged versions** of the SDK in production builds.
- ⚠️ Avoid using snapshot or dynamic versions (`main-SNAPSHOT`, `latest.release`) in release builds.
- 🧩 Keep analytics providers **stateless and lightweight**.
- 🔄 Prefer **batching events** to reduce network overhead.
- 📡 Handle network failures gracefully in custom providers.
- 🔐 Avoid sending sensitive or personally identifiable information (PII).

---

## 📄 License


---

## 🤝 Contributing

Contributions are welcome and appreciated!

### How to Contribute

1. Fork the repository
2. Create a feature branch  
   `git checkout -b feature/your-feature-name`
3. Commit your changes  
   `git commit -m "Add your message"`
4. Push to your fork  
   `git push origin feature/your-feature-name`
5. Open a Pull Request

Please ensure:
- Code follows Kotlin and Android best practices
- New features include appropriate documentation
- Changes do not break existing functionality

---

Thank you for helping improve the Analytics SDK 🚀

