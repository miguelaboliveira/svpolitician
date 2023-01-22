package com.miguelaboliveira.svpolitician.data.network.phares

import com.miguelaboliveira.svpolitician.data.network.phares.Phrase
import retrofit2.http.GET

public interface PhrasesApi {
    @GET("/api/json")
    public suspend fun getPhrase(): Phrase
}