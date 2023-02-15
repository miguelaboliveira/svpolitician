package com.miguelaboliveira.svpolitician.feature.home.domain

import com.miguelaboliveira.svpolitician.data.database.Phrase
import com.miguelaboliveira.svpolitician.data.database.SVPoliticianDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetMostRecentPhraseUseCase @Inject constructor(
    private val database: SVPoliticianDatabase
) {
    public operator fun invoke(): Flow<Phrase> =
        database.phraseQueries.selectMostRecent().asFlow().mapToOne()
}
