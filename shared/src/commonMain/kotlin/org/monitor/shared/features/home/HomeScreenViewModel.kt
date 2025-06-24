package org.monitor.shared.features.home

import org.koin.core.component.inject
import org.monitor.shared.base.mvi.BaseViewModel
import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.domain.entities.DateAndDay
import org.monitor.shared.domain.interactors.home.GetDateAndDayUseCase

open class HomeScreenViewModel : BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>(){


    private val getDateAndDayUseCase: GetDateAndDayUseCase by inject()

    private var homeInfoModel = HomeScreenInfoModel(
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


    override fun createInitialState(): HomeScreenContract.State {
        return HomeScreenContract.State(BasicUiState.Loading)
    }

    override fun handleEvent(event: HomeScreenContract.Event) {
        when (event){
            HomeScreenContract.Event.NavigateToAddStudentScreen -> setEffect {
                HomeScreenContract.Effect.NavigateToAddStudentScreen
            }
        }
    }
}