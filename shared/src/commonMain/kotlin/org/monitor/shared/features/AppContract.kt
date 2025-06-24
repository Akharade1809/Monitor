package org.monitor.shared.features

import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState
import org.monitor.shared.domain.entities.AppViewModelInfo

interface AppContract {
    sealed interface Event : UiEvent {

    }

    data class State(
        val appViewModelInfo : AppViewModelInfo
    ) : UiState

    sealed interface Effect : UiEffect {
        data object  NavigateToBrowse : Effect
    }
}