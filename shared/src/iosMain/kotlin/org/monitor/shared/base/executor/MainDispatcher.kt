package org.monitor.shared.base.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual open class MainDispatcher {
    actual open val dispatcher: CoroutineDispatcher = Dispatchers.Main
}