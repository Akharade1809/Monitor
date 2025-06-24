package org.monitor.shared.domain.interactors.base


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseInOut<IN, OUT> : BaseUseCase() {
    operator fun invoke(param: IN) : Flow<Result<OUT>> = flow {
        emit(
            try{
                val out : OUT = block(param)
                Result.success(out)
            }catch (ex : Exception) {
                Result.failure(exception = ex)
            }
        )
    }

    protected abstract suspend fun block(param: IN) : OUT
}

abstract class UseCaseInOutFlow<IN, OUT> : BaseUseCase() {
    operator fun invoke(param : IN): Flow<Result<OUT>> = try {
        build(param).map {Result.success(it)}
    }catch (ex : Exception){
        flowOf(Result.failure(ex))


    }

    protected abstract fun build(param: IN) : Flow<OUT>

}