package org.monitor.shared.features.student

import kotlinx.coroutines.flow.Flow
import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState
import org.monitor.shared.domain.entities.Student

class GetStudentListContract  {

    sealed interface Event : UiEvent {

    }

    data class State(
        val getStudentsListState : BasicUiState<GetStudentListInfoModel>
    ) : UiState

    sealed interface Effect : UiEffect {

    }
}


data class GetStudentListInfoModel(
    val studentList : List<Student>
)