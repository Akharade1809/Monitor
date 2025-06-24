package org.monitor.shared.base.executor.mvi

sealed interface BasicUiState<out T> {
    data class Sucess<T>(val data: T) : BasicUiState<T>
    data class Error(val message: String? = null) : BasicUiState<Nothing>

    object Loading : BasicUiState<Nothing>
    object Empty : BasicUiState<Nothing>
    object Idle : BasicUiState<Nothing>
    object Offline : BasicUiState<Nothing>
}