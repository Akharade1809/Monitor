package org.monitor.shared.domain.interactors.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseOut<OUT> : BaseUseCase() {
    operator fun invoke() : Flow<Result<OUT>> = flow {
        emit(
            try{
                val out : OUT = block()
                Result.success(out)
            }catch (ex : Exception) {
                Result.failure(exception = ex)
            }
        )
    }

    protected abstract suspend fun block() : OUT
}

abstract class UseCaseOutFlow<OUT> : BaseUseCase() {
    operator fun invoke(): Flow<Result<OUT>> = try {
        build().map {
            Result.success(it)
        }
    }catch (ex : Exception){
        flowOf(Result.failure(ex))


    }

    protected abstract fun build() : Flow<OUT>

}