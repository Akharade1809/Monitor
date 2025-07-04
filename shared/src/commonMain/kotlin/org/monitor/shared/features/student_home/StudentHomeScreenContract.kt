package org.monitor.shared.features.student_home

import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState

interface StudentHomeScreenContract {
    sealed interface Event : UiEvent{
        data object NavigateToAddStudentScreen : Event
        data object NavigateToGetStudentListScreen : Event
    }

    data class State(
        val homeInfo : BasicUiState<StudentHomeScreenInfoModel>
    ) : UiState

    sealed interface Effect : UiEffect{
        //Add effect on Click
        data object NavigateToAddStudentScreen : Effect
        data object NavigateToStudentListScreen : Effect
    }
}