package com.miguelaboliveira.svpolitician.data.ephemeral

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public inline fun <reified S> SavedStateHandle.ephemeralStore(
    initialState: S,
    key: String
): EphemeralStore<S> = ephemeralStore(
    initialState = initialState,
    readSavedState = { get(key) },
    writeSavedState = { set(key, it) },
    encode = Json::encodeToString,
    decode = Json::decodeFromString
)
