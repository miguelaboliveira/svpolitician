package com.miguelaboliveira.svpolitician.feature.home.domain

import com.miguelaboliveira.svpolitician.data.database.SVPoliticianDatabase
import com.miguelaboliveira.svpolitician.data.network.HttpApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

public class FetchPhraseUseCase @Inject constructor(
    private val database: SVPoliticianDatabase,
    private val httpApi: HttpApi
) {
    public suspend operator fun invoke(
        fetchingState: MutableStateFlow<Boolean>
    ): Unit = withContext(Dispatchers.IO) {
        fetchingState.update { true }
        runCatching {
            val phrase = httpApi.phraseApi.getPhrase()
            database.phraseQueries.insert(phrase.message, Instant.now())
        }.onFailure { exception: Throwable ->
            // TODO do something
            println("FetchPhraseUseCase $exception")
        }
        fetchingState.update { false }
    }
}
