package com.miguelaboliveira.svpolitician.core.ephemeral

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public inline fun <reified S> SavedStateHandle.ephemeralStore(
    initialState: S,
    key: String,
): EphemeralStore<S> =
    ephemeralStore(
        initialState = initialState,
        readSavedState = { get(key) },
        writeSavedState = { set(key, it) },
        encode = { Json.encodeToString(it) },
        decode = { Json.decodeFromString(it) },
    )
