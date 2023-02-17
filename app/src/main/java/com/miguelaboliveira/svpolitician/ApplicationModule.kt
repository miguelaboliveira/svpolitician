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
    @Named("versionName")
    public fun versionName(): String = BuildConfig.VERSION_NAME

    @Provides
    @Named("versionCode")
    public fun versionCode(): Int = BuildConfig.VERSION_CODE
}
