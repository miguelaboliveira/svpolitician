package com.miguelaboliveira.svpolitician.feature.home.ui

import com.miguelaboliveira.svpolitician.ui.error.UiError
import java.time.Instant

public data class HomeUiState(
    val loading: Boolean = true,
    val refreshing: Boolean = false,
    val phrase: Phrase? = null,
    val errors: List<UiError> = emptyList()
) {
    public data class Phrase(
        val message: String,
        val date: Instant
    )
}
