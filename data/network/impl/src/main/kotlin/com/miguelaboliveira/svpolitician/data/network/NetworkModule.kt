package com.miguelaboliveira.svpolitician.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public object NetworkModule {

    @Provides
    @Singleton
    public fun httpApi(
        @Named("baseUrl") baseUrl: String,
        @Named("debug") debug: Boolean
    ): HttpApi = HttpApiImpl(baseUrl, debug)
}
