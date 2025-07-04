package org.monitor.shared.features.splash

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.monitor.shared.base.mvi.BaseViewModel

open class SplashViewModel : BaseViewModel<SplashScreenContract.Event, SplashScreenContract.State, SplashScreenContract.Effect>(){
    override fun createInitialState(): SplashScreenContract.State {
       return SplashScreenContract.State
    }

    init {
        haveDelayAndNavigateToHome()
    }

    private fun haveDelayAndNavigateToHome(){
        launch {
            delay(10L)
            setEffect {
                SplashScreenContract.Effect.NavigateToHome
            }
        }
    }

    override fun handleEvent(event: SplashScreenContract.Event) {
        TODO("Not yet implemented")
    }

}