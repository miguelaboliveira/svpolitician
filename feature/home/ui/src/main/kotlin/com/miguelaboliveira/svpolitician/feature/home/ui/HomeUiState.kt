package com.miguelaboliveira.svpolitician.feature.home.ui

import java.time.Instant

public data class HomeUiState(
    val loading: Boolean,
    val refreshing: Boolean,
    val phrase: Phrase?
    // TODO Errors
) {
    public data class Phrase(
        val message: String,
        val date: Instant
    )
}
