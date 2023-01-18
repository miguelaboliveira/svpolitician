package com.miguelaboliveira.svpolitician.data.httpapi.phares

import retrofit2.http.GET

public interface PhrasesApi {
    @GET("/api/json")
    public suspend fun getPhrase(): Phrase
}