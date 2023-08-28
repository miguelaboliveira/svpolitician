package com.miguelaboliveira.svpolitician.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

public fun databaseFake(): SVPoliticianDatabase = SVPoliticianDatabase(
    driver = createSqlDriver(),
    PhraseAdapter = Phrase.Adapter(
        dateAdapter = InstantColumnAdapter,
    ),
)

private fun createSqlDriver(): SqlDriver = JdbcSqliteDriver(
    url = JdbcSqliteDriver.IN_MEMORY,
).apply {
    SVPoliticianDatabase.Schema.create(this)
}
