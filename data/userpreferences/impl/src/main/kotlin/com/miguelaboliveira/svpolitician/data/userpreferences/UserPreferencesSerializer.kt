package com.miguelaboliveira.svpolitician.data.userpreferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

@OptIn(ExperimentalSerializationApi::class)
internal object UserPreferencesSerializer : Serializer<UserPreferences> {

    override val defaultValue: UserPreferences = UserPreferences()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        try {
            return Json.decodeFromStream(input)
        } catch (exception: SerializationException) {
            throw CorruptionException("Cannot read json.", exception)
        }
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        Json.encodeToStream(t, output)
    }
}
