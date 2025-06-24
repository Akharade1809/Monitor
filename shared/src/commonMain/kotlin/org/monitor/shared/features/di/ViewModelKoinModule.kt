package org.monitor.shared.features.di

import org.koin.dsl.module
import org.monitor.shared.features.app.mvi.AppViewModel
import org.monitor.shared.features.home.HomeScreenViewModel
import org.monitor.shared.features.splash.SplashViewModel
import org.monitor.shared.features.student.AddStudentViewModel


val viewModelModule = module {
   single { AppViewModel() }
    single { HomeScreenViewModel() }
    single { SplashViewModel() }
    single { AddStudentViewModel() }

}

val viewModelKoinModule = viewModelModule