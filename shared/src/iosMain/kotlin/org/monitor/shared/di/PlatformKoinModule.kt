package org.monitor.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.monitor.shared.base.executor.MainDispatcher


actual fun platformKoinModule(): Module = module {
    single { MainDispatcher() }
//    single<DatabaseDriver> {DatabaseDriverImpl(get()).get}
}

actual annotation class CommonParcelize
actual interface CommonParcelable