package com.miguelaboliveira.svpolitician

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
public object ApplicationModule {

    @Provides
    @Named("baseUrl")
    public fun baseUrl(): String = "https://techy-api.vercel.app"

    @Provides
    @Named("debug")
    public fun debug(): Boolean = BuildConfig.DEBUG
}
