package com.miguelaboliveira.svpolitician.core.database

import android.app.Application
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public object DatabaseModule {
    @Provides
    @Singleton
    public fun database(application: Application): SVPoliticianDatabase =
        SVPoliticianDatabase(
            driver = createSqlDriver(application),
            PhraseAdapter =
                Phrase.Adapter(
                    dateAdapter = InstantColumnAdapter,
                ),
        )
}

private fun createSqlDriver(application: Application): SqlDriver =
    AndroidSqliteDriver(
        schema = SVPoliticianDatabase.Schema,
        context = application,
        name = "com.miguelaboliveira.svpolitician.data.database",
        callback =
            object : AndroidSqliteDriver.Callback(SVPoliticianDatabase.Schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    super.onConfigure(db)
                    db.enableWriteAheadLogging()
                    db.setForeignKeyConstraintsEnabled(true)
                }
            },
    )
