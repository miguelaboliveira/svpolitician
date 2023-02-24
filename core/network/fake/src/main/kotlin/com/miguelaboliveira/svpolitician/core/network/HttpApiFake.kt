package com.miguelaboliveira.svpolitician.core.network

import com.miguelaboliveira.svpolitician.core.network.phares.Phrase
import com.miguelaboliveira.svpolitician.core.network.phares.PhraseApi

public fun httpApiFake(
    phraseApi: PhraseApi = FakePhraseApi
): HttpApi = object : HttpApi {
    override val phraseApi: PhraseApi = phraseApi
}

private object FakePhraseApi : PhraseApi {
    override suspend fun getPhrase(): Phrase {
        return Phrase("Fake phrase")
    }
}
