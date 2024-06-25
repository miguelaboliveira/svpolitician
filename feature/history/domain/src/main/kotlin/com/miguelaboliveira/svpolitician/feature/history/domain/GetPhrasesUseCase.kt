package com.miguelaboliveira.svpolitician.feature.history.domain

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.miguelaboliveira.svpolitician.core.database.Phrase
import com.miguelaboliveira.svpolitician.core.database.SVPoliticianDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetPhrasesUseCase
    @Inject
    constructor(
        private val database: SVPoliticianDatabase,
    ) {
        public operator fun invoke(): Flow<List<Phrase>> =
            database.phraseQueries
                .selectAll()
                .asFlow()
                .mapToList(Dispatchers.IO)
    }
