package org.monitor.shared.features.splash

import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState

interface SplashScreenContract {

    sealed interface Event: UiEvent
    data object State : UiState
    sealed interface Effect : UiEffect {
        data object NavigateToHome : Effect
    }
}