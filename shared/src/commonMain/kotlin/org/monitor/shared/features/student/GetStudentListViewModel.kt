package org.monitor.shared.features.student

import org.koin.core.component.inject
import org.monitor.shared.base.executor.mvi.BasicUiState
import org.monitor.shared.base.mvi.BaseViewModel
import org.monitor.shared.domain.interactors.student_home.GetStudentListUseCase

class GetStudentListViewModel :
    BaseViewModel<GetStudentListContract.Event, GetStudentListContract.State, GetStudentListContract.Effect>() {

    private val getStudentListUseCase: GetStudentListUseCase by inject()
    private var getStudentListInfoModel: GetStudentListInfoModel = GetStudentListInfoModel(listOf())

    override fun createInitialState(): GetStudentListContract.State {
        return GetStudentListContract.State(BasicUiState.Sucess(getStudentListInfoModel))
    }

    init {
        fetchStudentList()
    }

    private fun fetchStudentList() {
        collect(getStudentListUseCase()) { result ->
            println("fetching the students list: $result")
            result.onSuccess { studentlist ->
                getStudentListInfoModel = getStudentListInfoModel.copy(studentList = studentlist)
                setState {
                    copy(getStudentsListState = BasicUiState.Sucess(getStudentListInfoModel))
                }
            }
            result.onFailure {
                println("error fetching student list: $it")
                setState {
                    copy(getStudentsListState = BasicUiState.Error("Failed to fetch student list"))
                }
            }
        }
    }

    override fun handleEvent(event: GetStudentListContract.Event) {
        TODO("Not yet implemented")
    }
}