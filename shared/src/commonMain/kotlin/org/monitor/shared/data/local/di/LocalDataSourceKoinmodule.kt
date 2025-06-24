package org.monitor.shared.data.local.di

import org.koin.dsl.module
import org.monitor.shared.data.local.datasource.StudentLocalDataSource
import org.monitor.shared.data.local.datasourceImpl.StudentLocalDataSourceImpl

private val localDataSourceModule = module {
    single<StudentLocalDataSource>{StudentLocalDataSourceImpl()}
}

val localDataSourceKoinModule = localDataSourceModule