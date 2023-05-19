package com.miguelaboliveira.svpolitician.feature.history.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.time.Instant

public data class HistoryUiState(
    val loading: Boolean = true,
    val phrases: ImmutableList<Phrase> = persistentListOf(),
) {
    public data class Phrase(
        val id: Long,
        val message: String,
        val date: Instant,
    )
}
