package org.monitor.shared.base.mvi

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.monitor.shared.base.executor.MainIoExecutor
import org.monitor.shared.base.executor.mvi.UiEffect
import org.monitor.shared.base.executor.mvi.UiEvent
import org.monitor.shared.base.executor.mvi.UiState

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : MainIoExecutor() {

    private val initialState : State by lazy { createInitialState() }

    abstract fun createInitialState(): State

    val currentState : State
        get() = uiState.value


    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState : StateFlow<State> = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }


    private fun subscribeEvents(){
        launch {
            event.collect{
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: Event)

    fun setEvent(event: Event){
        val newEvent = event
        launch { _event.emit(newEvent) }
    }

    protected fun setState(reduce: State.() -> State){
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEffect(builder: () -> Effect){
        val effectValue = builder()
        launch { _effect.send(effectValue) }
    }

    fun onCleared(){
        setState { createInitialState() }
    }

}