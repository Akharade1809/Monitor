package org.monitor.shared.data.repositoriesImpl.di

import org.koin.dsl.module
import org.monitor.shared.data.repositoriesImpl.DateAndDayRepositoryImpl
import org.monitor.shared.data.repositoriesImpl.StudentRepositoryImpl
import org.monitor.shared.domain.repositories.DateAndDayRepository
import org.monitor.shared.domain.repositories.StudentRepository

private val repositoryModule = module {
    single<DateAndDayRepository> { DateAndDayRepositoryImpl() }
    single<StudentRepository> {StudentRepositoryImpl(get())}
}

val repositoryKoinModule = repositoryModule