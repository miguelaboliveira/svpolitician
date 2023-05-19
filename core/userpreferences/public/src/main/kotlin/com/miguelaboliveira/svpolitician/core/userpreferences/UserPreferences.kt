package com.miguelaboliveira.svpolitician.core.userpreferences

import kotlinx.serialization.Serializable

@Serializable
public data class UserPreferences(
    val syncIntervalMinutes: Long = 15,
)
