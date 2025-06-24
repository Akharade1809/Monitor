package org.monitor.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.monitor.shared.base.di.sharedDispatcherKoinModule
import org.monitor.shared.data.local.di.localDataSourceKoinModule
import org.monitor.shared.data.repositoriesImpl.di.repositoryKoinModule
import org.monitor.shared.domain.interactors.di.useCaseKoinModule
import org.monitor.shared.features.di.viewModelKoinModule

fun initKoin(koinAppDeclaration: KoinAppDeclaration = {}) = startKoin {
    koinAppDeclaration()
    modules(
        platformKoinModule(),
        viewModelKoinModule,
        localDataSourceKoinModule,
        repositoryKoinModule,
        useCaseKoinModule,
        sharedDispatcherKoinModule

    )
}