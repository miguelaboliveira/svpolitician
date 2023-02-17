package com.miguelaboliveira.svpolitician.feature.home.domain

import com.miguelaboliveira.svpolitician.data.database.SVPoliticianDatabase
import com.miguelaboliveira.svpolitician.data.network.HttpApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

public class FetchPhraseUseCase @Inject constructor(
    private val database: SVPoliticianDatabase,
    private val httpApi: HttpApi
) {
    public suspend operator fun invoke(): Unit = withContext(Dispatchers.IO) {
        val phrase = httpApi.phraseApi.getPhrase()
        database.phraseQueries.insert(phrase.message, Instant.now())
    }
}
