package com.miguelaboliveira.svpolitician.feature.settings.domain

import com.miguelaboliveira.svpolitician.core.userpreferences.UserPreferencesStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetSyncIntervalUseCase
    @Inject
    constructor(
        private val userPreferencesStore: UserPreferencesStore,
    ) {
        public operator fun invoke(): Flow<Long> =
            userPreferencesStore.asFlow { it.syncIntervalMinutes }
    }
