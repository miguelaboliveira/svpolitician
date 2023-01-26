package com.miguelaboliveira.svpolitician.data.network.phares

import retrofit2.http.GET

public interface PhraseApi {
    @GET("/api/json")
    public suspend fun getPhrase(): Phrase
}
