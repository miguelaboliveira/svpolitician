package com.miguelaboliveira.svpolitician.core.userpreferences

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public object UserPreferencesModule {

    @Provides
    @Singleton
    public fun userPreferencesStore(
        application: Application
    ): UserPreferencesStore = UserPreferencesStoreImpl(application)
}
