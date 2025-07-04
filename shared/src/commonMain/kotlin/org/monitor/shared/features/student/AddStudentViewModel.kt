package org.monitor.shared.features.student

import org.monitor.shared.domain.entities.Student
import org.koin.core.component.inject
import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.base.mvi.BaseViewModel
import org.monitor.shared.domain.interactors.student_home.AddStudentUseCase
import org.monitor.shared.extensions.isValidMobileNumber
import org.monitor.shared.extensions.isValidName

open class AddStudentViewModel :
    BaseViewModel<AddStudentContract.Event, AddStudentContract.State, AddStudentContract.Effect>() {


        private var addStudentInfoModel : AddStudentInfoModel = AddStudentInfoModel()

    private val addStudentUseCase : AddStudentUseCase by inject()


    override fun createInitialState(): AddStudentContract.State {
       return AddStudentContract.State(BasicUiState.Sucess(AddStudentInfoModel()))
    }

    override fun handleEvent(event: AddStudentContract.Event) {
        when (event){
            AddStudentContract.Event.OnBackPressed -> setEffect { AddStudentContract.Effect.BackNavigation }
            is AddStudentContract.Event.AddStudent -> initiateAddition(event)
            is AddStudentContract.Event.OnFormEdited -> validateForm(event)
            AddStudentContract.Event.NavigateOnSuccessfulAddition -> setEffect { AddStudentContract.Effect.NavigateOnSuccessfulAddition }
        }
    }

    private fun initiateAddition(event: AddStudentContract.Event.AddStudent){
        collect(addStudentUseCase(
            param = Student(
                studentId = toString(),
                studentFirstName = event.firstName,
                studentLastName = event.lastName,
                studentMobileNumber = event.mobileNumber,
                studentWhatsappNumber = event.whatsappNumber,
                studentSchoolName = event.schoolName,
                studentClass = event.studentClass,
            )
        )){result ->
            result.onSuccess { student ->
                println("succesfully added the Student : ${student}")
                setEvent(AddStudentContract.Event.NavigateOnSuccessfulAddition)
            }
        }
    }

    private fun validateForm(event: AddStudentContract.Event.OnFormEdited) {
        val isFirstNameValid: Boolean = event.firstName.isValidName()
        val isLastNameValid: Boolean = event.lastName.isValidName()
        val isMobileNumberValid = event.mobileNumber.isValidMobileNumber()
        val isWhatsappNumberValid = event.whatsappNumber.isValidMobileNumber()

        val isAddStudentButtonEnabled: Boolean =
            isFirstNameValid.and(isLastNameValid).and(isMobileNumberValid).and(isWhatsappNumberValid)

        addStudentInfoModel = addStudentInfoModel.copy(
            isFirstNameValid = isFirstNameValid,
            isLastNameValid = isLastNameValid,
            isMobileNumberValid = isMobileNumberValid,
            isWhatsappNumberValid = isWhatsappNumberValid,
            isAddStudentButtonEnabled = isAddStudentButtonEnabled
        )

        setState {
            copy(addStudentState = BasicUiState.Sucess(addStudentInfoModel))
        }
    }
}