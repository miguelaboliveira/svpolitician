package com.miguelaboliveira.svpolitician.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.miguelaboliveira.svpolitician.data.network.phares.PhrasesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

public class HttpApiImpl(
    baseUrl: String
) : HttpApi {
    override val phrasesApi: PhrasesApi by lazy { retrofit.create() }

    private val retrofit: Retrofit by lazy {
        createRetrofit(
            baseUrl = baseUrl,
            okHttpClient = createOkHttpClient()
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okHttpClient)
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    private fun createOkHttpClient() = OkHttpClient.Builder()
        .apply {
            // Add interceptors
        }
        .build()
}