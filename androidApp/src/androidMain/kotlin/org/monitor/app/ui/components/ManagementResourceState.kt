package org.monitor.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.monitor.shared.base.executor.mvi.BasicUiState

@Composable
fun <T> ManagementResourceState(
    resourceState : BasicUiState<T>,
    successView : @Composable (data: T?) -> Unit,
    modifier: Modifier = Modifier.fillMaxSize(),
    onTryAgain: () -> Unit,
    msgTryAgain: String = " No data to show",
    onCheckAgain : () -> Unit = {},
    msgOnCheckAgain : String = "An error has occurred"
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.systemBarsPadding()
    ){
        when (resourceState){
            is BasicUiState.Sucess -> successView(resourceState.data)
            BasicUiState.Empty -> {}
            is BasicUiState.Error -> Error(onTryAgain = onTryAgain, msg = resourceState.message ?: msgTryAgain)
            BasicUiState.Idle -> successView(null)
            BasicUiState.Loading -> {}
            BasicUiState.Offline -> {}
        }
    }
}