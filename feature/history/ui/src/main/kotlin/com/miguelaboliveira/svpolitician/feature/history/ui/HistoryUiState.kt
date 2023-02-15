package com.miguelaboliveira.svpolitician.feature.history.ui

import java.time.Instant

public data class HistoryUiState(
    val loading: Boolean,
    val phrases: List<Phrase>
) {
    public data class Phrase(
        val id: Long,
        val message: String,
        val date: Instant
    )
}
