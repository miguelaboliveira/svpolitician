package com.miguelaboliveira.svpolitician.core.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

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
