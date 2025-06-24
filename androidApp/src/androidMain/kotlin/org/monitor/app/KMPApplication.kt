package org.monitor.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.monitor.shared.base.di.sharedDispatcherKoinModule
import org.monitor.shared.data.local.di.localDataSourceKoinModule
import org.monitor.shared.data.repositoriesImpl.di.repositoryKoinModule
import org.monitor.shared.di.platformKoinModule
import org.monitor.shared.domain.interactors.di.useCaseKoinModule
import org.monitor.shared.features.di.viewModelKoinModule

class KMPApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("Starting the KmpApplication")
        startKoin {
            androidContext(this@KMPApplication)
            modules(
                listOf(
                    viewModelKoinModule,
                    localDataSourceKoinModule,
                    platformKoinModule(),
                    repositoryKoinModule,
                    useCaseKoinModule,
                    sharedDispatcherKoinModule
                )
            )

        }
}
    companion object{
        @SuppressLint("StaticFieldLeak")
        var currentActivity: Activity? = null
    }
}