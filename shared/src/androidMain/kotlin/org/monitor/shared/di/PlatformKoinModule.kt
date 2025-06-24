package org.monitor.shared.di

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.koin.core.module.Module
import org.koin.dsl.module
import org.monitor.shared.base.executor.MainDispatcher
import org.monitor.shared.data.factory.DatabaseDriver
import org.monitor.shared.data.factory.DatabaseDriverImpl

actual fun platformKoinModule(): Module = module {
    single { MainDispatcher() }
    single<DatabaseDriver> { DatabaseDriverImpl(get()).getDriver() }

}

actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable