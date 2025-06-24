package org.monitor.shared.domain.interactors.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCase : BaseUseCase() {
    operator fun invoke(): Flow<Result<Unit>> = flow {
        emit(
            try {
                Result.success(block())
            } catch (ex: Exception) {
                Result.failure(exception = ex)
            }
        )
    }
    protected abstract suspend fun block()
}

abstract class UseCaseFlow: BaseUseCase() {
    operator fun invoke() : Flow<Result<Unit>> = try {
        build().map { Result.success(it) }
    }catch (ex : Exception) {
        flowOf(Result.failure(exception = ex))
    }
    protected abstract fun build() : Flow<Unit>
}