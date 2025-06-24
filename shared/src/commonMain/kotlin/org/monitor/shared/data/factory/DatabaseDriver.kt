package org.monitor.shared.data.factory

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriver {
    fun createDriver() : SqlDriver
}