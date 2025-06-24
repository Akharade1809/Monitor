package org.monitor.shared.base.executor

import kotlinx.coroutines.CoroutineDispatcher

expect open class MainDispatcher() {
    open val dispatcher : CoroutineDispatcher
}