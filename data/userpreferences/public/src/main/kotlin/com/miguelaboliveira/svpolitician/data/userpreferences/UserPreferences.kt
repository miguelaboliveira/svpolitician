package com.miguelaboliveira.svpolitician.data.userpreferences

import kotlinx.serialization.Serializable

@Serializable
public data class UserPreferences(
    val syncIntervalMinutes: Long = 15
)
