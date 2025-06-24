package org.monitor.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.monitor.shared.base.executor.MainDispatcher

expect fun platformKoinModule() : Module

expect annotation class CommonParcelize()

expect interface CommonParcelable