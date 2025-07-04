package org.monitor.shared.features.student

import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState

interface AddStudentContract {

    sealed interface Event : UiEvent {

        data class AddStudent(
            val firstName: String,
            val lastName: String,
            val mobileNumber: String,
            val whatsappNumber: String,
            val schoolName: String,
            val studentClass : Double
        ) : Event

        data object OnBackPressed : Event

        data class OnFormEdited(
            val firstName: String,
            val lastName: String,
            val mobileNumber: String,
            val whatsappNumber: String,
            val schoolName: String,
            val studentClass: Double
        ) : Event

        data object NavigateOnSuccessfulAddition :  Event
    }

    data class State(
        val addStudentState : BasicUiState<AddStudentInfoModel>
    ): UiState

    sealed interface Effect : UiEffect {
        data object BackNavigation : Effect
        data object NavigateOnSuccessfulAddition : Effect
        data object NavigateToStudentListPage : Effect
    }
}


data class AddStudentInfoModel(
    var isFirstNameValid : Boolean = true,
    var isLastNameValid : Boolean = true,
    var isMobileNumberValid : Boolean = true,
    var isWhatsappNumberValid : Boolean = true,
    var isSchoolNameValid : Boolean = true,
    var isAddStudentButtonEnabled : Boolean = false
)