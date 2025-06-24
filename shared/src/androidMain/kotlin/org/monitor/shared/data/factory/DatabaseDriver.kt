package org.monitor.shared.data.factory

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import data.repositoryImpl.local.databases.MonitorDatabase

actual class DatabaseDriverImpl(private val context : Context) {
    actual fun getDriver(): DatabaseDriver {
        return AndroidDatabaseDriverImpl(context)
    }
}

class AndroidDatabaseDriverImpl(private val context: Context) : DatabaseDriver {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = MonitorDatabase.Schema.synchronous()       ,
            context = context,
            name = "monitor.db",
            callback = object :
            AndroidSqliteDriver.Callback(MonitorDatabase.Schema.synchronous()){
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}