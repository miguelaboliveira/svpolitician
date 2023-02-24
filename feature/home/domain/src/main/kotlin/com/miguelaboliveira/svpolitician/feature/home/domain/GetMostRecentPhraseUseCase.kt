package com.miguelaboliveira.svpolitician.feature.home.domain

import com.miguelaboliveira.svpolitician.core.database.Phrase
import com.miguelaboliveira.svpolitician.core.database.SVPoliticianDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneNotNull
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetMostRecentPhraseUseCase @Inject constructor(
    private val database: SVPoliticianDatabase
) {
    public operator fun invoke(): Flow<Phrase?> =
        database.phraseQueries.selectMostRecent().asFlow().mapToOneNotNull()
}
