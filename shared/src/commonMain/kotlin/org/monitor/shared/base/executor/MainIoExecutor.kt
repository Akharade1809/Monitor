package org.monitor.shared.base.executor

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import kotlin.coroutines.CoroutineContext

abstract class MainIoExecutor : IExecutorScope, CoroutineScope, KoinComponent {

    private val mainDispatcher: MainDispatcher by inject()
    private val ioDispatcher : CoroutineDispatcher by inject()

    private val job: CompletableJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + mainDispatcher.dispatcher

    override fun cancel() {
        job.cancel()
    }


    protected fun <T> collect (
        flow: Flow<T>, collect: (T) -> Unit
    ){
        launch {
            flow
                .flowOn(ioDispatcher)
                .collect{
                    collect(it)
                }
        }
    }

    protected fun <T1, T2> collect (
        flow1: Flow<T1>, flow2: Flow<T2>, collect: (T1, T2) -> Unit
    ){
        launch {
            combine(flow1, flow2) { t1, t2 ->
                Pair(t1, t2)
            }
                .flowOn(ioDispatcher)
                .collect{ pair->
                    collect(pair.first, pair.second)
                }
        }
    }

    protected fun <T1, T2, T3> collect (
        flow1: Flow<T1>, flow2: Flow<T2>, flow3 : Flow<T3>, collect: (T1, T2, T3) -> Unit
    ){
        launch {
            combine(flow1, flow2, flow3) { t1, t2, t3 ->
                Triple(t1, t2, t3)
            }
                .flowOn(ioDispatcher)
                .collect{ triple->
                    collect(triple.first, triple.second, triple.third)
                }
        }
    }

}