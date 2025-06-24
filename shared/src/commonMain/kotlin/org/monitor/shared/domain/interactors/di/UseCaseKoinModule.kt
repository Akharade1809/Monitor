package org.monitor.shared.domain.interactors.di

import org.koin.dsl.module
import org.monitor.shared.domain.interactors.home.GetDateAndDayUseCase
import org.monitor.shared.domain.interactors.student.AddStudentUseCase

private val useCaseModule = module {
    factory { GetDateAndDayUseCase(get()) }
    factory { AddStudentUseCase(get()) }
}

val useCaseKoinModule = useCaseModule