package org.monitor.shared.data.local.datasource

import data.repositoryImpl.local.databases.MonitorDatabase
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.monitor.shared.data.factory.DatabaseDriver

interface BaseLocalDataSource : KoinComponent {
    private val databaseDriver : DatabaseDriver
        get() = get()
    val database : MonitorDatabase
        get() = MonitorDatabase(databaseDriver.createDriver())
    val jsonParcer : Json
        get() = Json.Default
}