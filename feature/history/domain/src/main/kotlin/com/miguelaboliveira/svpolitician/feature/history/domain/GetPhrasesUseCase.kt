package com.miguelaboliveira.svpolitician.feature.history.domain

import com.miguelaboliveira.svpolitician.core.database.Phrase
import com.miguelaboliveira.svpolitician.core.database.SVPoliticianDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetPhrasesUseCase @Inject constructor(
    private val database: SVPoliticianDatabase
) {
    public operator fun invoke(): Flow<List<Phrase>> =
        database.phraseQueries.selectAll().asFlow().mapToList()
}
