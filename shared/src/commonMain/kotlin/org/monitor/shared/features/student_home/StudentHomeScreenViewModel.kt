package org.monitor.shared.features.student_home

import org.koin.core.component.inject
import org.monitor.shared.base.mvi.BaseViewModel
import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.domain.entities.DateAndDay
import org.monitor.shared.domain.interactors.student_home.GetDateAndDayUseCase

open class StudentHomeScreenViewModel : BaseViewModel<StudentHomeScreenContract.Event, StudentHomeScreenContract.State, StudentHomeScreenContract.Effect>(){


    private val getDateAndDayUseCase: GetDateAndDayUseCase by inject()

    private var homeInfoModel = StudentHomeScreenInfoModel(
        date = "",
        day = ""
    )

    init {
        fetchDateAndDay()
    }

    private fun fetchDateAndDay(){
        collect(getDateAndDayUseCase()){ result : Result<DateAndDay> ->
            result.onSuccess { date->
                homeInfoModel = homeInfoModel.copy(date = date.date, day = date.day)
                setState {copy(homeInfo = BasicUiState.Sucess(homeInfoModel))}
            }
            result.onFailure {
                setState { copy(homeInfo= BasicUiState.Error(message = "error fetching Date and Day")) }
            }
        }
    }


    override fun createInitialState(): StudentHomeScreenContract.State {
        return StudentHomeScreenContract.State(BasicUiState.Loading)
    }

    override fun handleEvent(event: StudentHomeScreenContract.Event) {
        when (event){
            StudentHomeScreenContract.Event.NavigateToAddStudentScreen -> setEffect {
                StudentHomeScreenContract.Effect.NavigateToAddStudentScreen
            }
            StudentHomeScreenContract.Event.NavigateToGetStudentListScreen -> setEffect {
                StudentHomeScreenContract.Effect.NavigateToStudentListScreen
            }
        }
    }
}