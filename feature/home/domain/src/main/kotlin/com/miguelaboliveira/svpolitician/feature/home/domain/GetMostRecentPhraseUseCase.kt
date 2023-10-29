package com.miguelaboliveira.svpolitician.feature.home.domain

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.miguelaboliveira.svpolitician.core.database.Phrase
import com.miguelaboliveira.svpolitician.core.database.SVPoliticianDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetMostRecentPhraseUseCase
    @Inject
    constructor(
        private val database: SVPoliticianDatabase,
    ) {
        public operator fun invoke(): Flow<Phrase?> =
            database.phraseQueries.selectMostRecent().asFlow().mapToOneOrNull(Dispatchers.IO)
    }
