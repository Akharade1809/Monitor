package org.monitor.shared.features.di

import org.koin.dsl.module
import org.monitor.shared.features.app.mvi.AppViewModel
import org.monitor.shared.features.student_home.StudentHomeScreenViewModel
import org.monitor.shared.features.splash.SplashViewModel
import org.monitor.shared.features.student.AddStudentViewModel
import org.monitor.shared.features.student.GetStudentListViewModel


val viewModelModule = module {
   single { AppViewModel() }
    single { StudentHomeScreenViewModel() }
    single { SplashViewModel() }
    single { AddStudentViewModel() }
    single { GetStudentListViewModel() }

}

val viewModelKoinModule = viewModelModule