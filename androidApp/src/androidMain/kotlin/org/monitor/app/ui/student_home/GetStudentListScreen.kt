package org.monitor.app.ui.student_home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import org.monitor.app.ui.components.ManagementResourceState
import org.monitor.shared.features.student.GetStudentListContract

@Composable
fun GetStudentListScreen(
    navController: NavController,
    onUiEvent : (GetStudentListContract.Event) -> Unit,
    uiState: StateFlow<GetStudentListContract.State>,
    uiEffect: Flow<GetStudentListContract.Effect>
) {

    val state by uiState.collectAsStateWithLifecycle()

//    LaunchedEffect(key1 = Unit) {
//        uiEffect.collectLatest { effect ->
//            when(effect){
//
//            }
//        }
//    }

    ManagementResourceState(
        resourceState = state.getStudentsListState,
        successView = { getStudentListInfoModel ->
            if (getStudentListInfoModel == null) return@ManagementResourceState
            Scaffold(

            ) { innerPadding ->
                LazyColumn (contentPadding = innerPadding){
                    items(getStudentListInfoModel.studentList){student ->
                        StudentCard(student = student)
                    }
                }

            }
        },
        onTryAgain = {}
    )
}