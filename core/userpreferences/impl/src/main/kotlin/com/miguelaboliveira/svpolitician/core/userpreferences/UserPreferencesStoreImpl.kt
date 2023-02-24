package com.miguelaboliveira.svpolitician.core.userpreferences

import android.app.Application
import androidx.datastore.core.DataStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.File

public class UserPreferencesStoreImpl(
    application: Application
) : UserPreferencesStore {

    private val dataStore = DataStoreFactory.create(
        serializer = UserPreferencesSerializer,
        produceFile = { File(application.filesDir, "datastore/user_preferences") }
    )

    override suspend fun get(): UserPreferences = dataStore.data.first()

    override suspend fun update(transform: (UserPreferences) -> UserPreferences) {
        dataStore.updateData { transform(it) }
    }

    override fun <T> asFlow(mapper: (value: UserPreferences) -> T): Flow<T> =
        dataStore.data.map(mapper).distinctUntilChanged()
}
