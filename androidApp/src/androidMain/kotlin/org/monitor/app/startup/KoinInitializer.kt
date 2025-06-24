package org.monitor.app.startup

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.startup.Initializer
import org.monitor.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class KoinInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        initKoin{
            androidLogger(if (context.isDebug()) Level.ERROR else Level.NONE)
            androidContext(context)
            modules(
            )
        }
    }

    /**
     * Gets a list of this initializer's dependencies.
     *
     * Dependencies are initialized before the dependent initializer. For
     * example, if initializer A defines initializer B as a dependency, B is
     * initialized before A.
     *
     * @return A list of initializer dependencies.
     */
    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}

fun Context.isDebug() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE