package com.miguelaboliveira.svpolitician.core.network.phares

import retrofit2.http.GET

public interface PhraseApi {
    @GET("/api/json")
    public suspend fun getPhrase(): Phrase
}
