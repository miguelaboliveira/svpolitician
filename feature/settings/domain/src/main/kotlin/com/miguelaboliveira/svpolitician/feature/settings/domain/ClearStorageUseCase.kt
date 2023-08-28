package com.miguelaboliveira.svpolitician.feature.settings.domain

import com.miguelaboliveira.svpolitician.core.database.SVPoliticianDatabase
import javax.inject.Inject

public class ClearStorageUseCase @Inject constructor(
    private val database: SVPoliticianDatabase,
) {
    public operator fun invoke(): Unit = database.phraseQueries.deleteAll()
}
