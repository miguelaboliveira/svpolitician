package com.miguelaboliveira.svpolitician.data.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

public fun database(): SVPoliticianDatabase = SVPoliticianDatabase(
    driver = createSqlDriver()
)

private fun createSqlDriver(): SqlDriver = JdbcSqliteDriver(
    url = JdbcSqliteDriver.IN_MEMORY
).apply {
    SVPoliticianDatabase.Schema.create(this)
}
