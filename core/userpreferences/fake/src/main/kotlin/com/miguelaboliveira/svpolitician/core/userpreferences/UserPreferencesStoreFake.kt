package com.miguelaboliveira.svpolitician.core.userpreferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

public fun userPreferencesStoreFake(
    defaultValue: UserPreferences = UserPreferences(),
): UserPreferencesStore = UserPreferencesStoreFake(defaultValue)

private class UserPreferencesStoreFake(
    defaultValue: UserPreferences,
) : UserPreferencesStore {
    private var userPreferences = MutableStateFlow(defaultValue)

    override suspend fun get(): UserPreferences = userPreferences.value

    override suspend fun update(transform: (UserPreferences) -> UserPreferences) {
        userPreferences.update(transform)
    }

    override fun <T> asFlow(mapper: (value: UserPreferences) -> T): Flow<T> =
        userPreferences.asStateFlow().map(mapper).distinctUntilChanged()
}
