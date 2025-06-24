package org.monitor.shared.features.home

import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState

interface HomeScreenContract {
    sealed interface Event : UiEvent{
        data object NavigateToAddStudentScreen : Event
    }

    data class State(
        val homeInfo : BasicUiState<HomeScreenInfoModel>
    ) : UiState

    sealed interface Effect : UiEffect{
        //Add effect on Click
        data object NavigateToAddStudentScreen : Effect
    }
}