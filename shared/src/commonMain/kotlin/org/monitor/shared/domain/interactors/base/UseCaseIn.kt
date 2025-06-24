package org.monitor.shared.domain.interactors.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseIn<IN> : BaseUseCase() {
    operator fun invoke(param: IN) : Flow<Result<Unit>> = flow {
        emit(
            try{
                Result.success(block(param))
            }catch (ex : Exception) {
                Result.failure(exception = ex)
            }
        )
    }

    protected abstract suspend fun block(param: IN)
}

abstract class UseCaseInFlow<IN> : BaseUseCase() {
    operator fun invoke(param : IN): Flow<Result<Unit>> = try {
        build(param).map {Result.success(it)}
    }catch (ex : Exception){
        flowOf(Result.failure(ex))


    }

    protected abstract fun build(param: IN) : Flow<Unit>

}