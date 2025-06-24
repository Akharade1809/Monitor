package org.monitor.shared.base.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sharedDispatcherModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }
}

val sharedDispatcherKoinModule = sharedDispatcherModule