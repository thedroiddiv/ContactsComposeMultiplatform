package com.plcoding.contactscomposemultiplatform.core.data


import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.plcoding.contactscomposemultiplatform.database.ContactDatabase
import java.io.File
import java.util.Properties

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val userHome = System.getProperty("user.home")
        val appDir = File("$userHome/.contactsApp/db")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        val driver = JdbcSqliteDriver(
            url = JdbcSqliteDriver.IN_MEMORY + "${appDir.path}/contacts.db"
        )
        driver.initializeDatabase()
        return driver
    }
}

fun JdbcSqliteDriver.initializeDatabase() {
    val currentVersion = this.getVersion()
    if (currentVersion == 0L) {
        ContactDatabase.Schema.create(this)
        this.setVersion(1)
        println("ContactsComposeMultiplatform: init db: created tables, setVersion to 1")
    } else {
        val schemaVersion = ContactDatabase.Schema.version
        if (schemaVersion > currentVersion) {
            ContactDatabase.Schema.migrate(this, currentVersion, schemaVersion)
            this.setVersion(schemaVersion)
            println("ContactsComposeMultiplatform: init db: Migrated db from $currentVersion to $schemaVersion")
        } else {
            println("ContactsComposeMultiplatform: init db:")
        }
    }
}

private fun JdbcSqliteDriver.getVersion(): Long {
    val queryResult = executeQuery(
        identifier = null,
        sql = "PRAGMA user_version;",
        mapper = { sqlCursor: SqlCursor ->
            QueryResult.Value(sqlCursor.getLong(0)!!)
        },
        parameters = 0,
        binders = null
    )
    return queryResult.value
}

private fun JdbcSqliteDriver.setVersion(version: Long): Long {
    return execute(
        identifier = null,
        sql = "PRAGMA user_version = $version;",
        parameters = 0,
        null
    ).value
}

