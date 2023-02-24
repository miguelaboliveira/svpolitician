package com.miguelaboliveira.svpolitician.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.miguelaboliveira.svpolitician.core.network.impl.BuildConfig
import com.miguelaboliveira.svpolitician.core.network.phares.PhraseApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

public class HttpApiImpl(
    baseUrl: String
) : HttpApi {
    override val phraseApi: PhraseApi by lazy { retrofit.create() }

    private val retrofit: Retrofit by lazy {
        createRetrofit(
            baseUrl = baseUrl,
            okHttpClient = createOkHttpClient()
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private fun createOkHttpClient() = OkHttpClient.Builder()
        .apply {
            addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
        }
        .build()
}
