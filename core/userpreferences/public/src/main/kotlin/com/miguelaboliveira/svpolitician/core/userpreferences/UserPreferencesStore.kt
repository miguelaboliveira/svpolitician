package com.miguelaboliveira.svpolitician.core.userpreferences

import kotlinx.coroutines.flow.Flow

public interface UserPreferencesStore {
    public suspend fun get(): UserPreferences
    public suspend fun update(transform: (UserPreferences) -> UserPreferences)

    public fun <T> asFlow(mapper: (value: UserPreferences) -> T): Flow<T>
}
