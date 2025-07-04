package org.monitor.shared.domain.interactors.di

import org.koin.dsl.module
import org.monitor.shared.domain.interactors.student_home.GetDateAndDayUseCase
import org.monitor.shared.domain.interactors.student_home.AddStudentUseCase

private val useCaseModule = module {
    factory { GetDateAndDayUseCase(get()) }
    factory { AddStudentUseCase(get()) }
    factory { GetDateAndDayUseCase(get()) }
}

val useCaseKoinModule = useCaseModule