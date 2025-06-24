package org.monitor.shared.data.factory

expect class DatabaseDriverImpl {
    fun getDriver(): DatabaseDriver
}