package com.miguelaboliveira.svpolitician.feature.settings.ui

import java.time.Duration

public data class SettingsUiState(
    val syncInterval: Duration,
    val versionName: String,
    val versionCode: Int
)
