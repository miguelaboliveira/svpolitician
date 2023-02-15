package com.miguelaboliveira.svpolitician.feature.history.ui

import kotlinx.collections.immutable.ImmutableList
import java.time.Instant

public data class HistoryUiState(
    val loading: Boolean,
    val phrases: ImmutableList<Phrase>
) {
    public data class Phrase(
        val id: Long,
        val message: String,
        val date: Instant
    )
}
