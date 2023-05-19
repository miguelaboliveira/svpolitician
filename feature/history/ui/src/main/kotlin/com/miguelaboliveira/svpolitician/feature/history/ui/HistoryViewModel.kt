package com.miguelaboliveira.svpolitician.feature.history.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelaboliveira.svpolitician.feature.history.domain.GetPhrasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
public class HistoryViewModel @Inject constructor(
    getPhrasesUseCase: GetPhrasesUseCase,
) : ViewModel() {

    public val uiState: StateFlow<HistoryUiState> = getPhrasesUseCase()
        .map { phrases ->
            HistoryUiState(
                loading = false,
                phrases = phrases.map {
                    HistoryUiState.Phrase(
                        id = it.id,
                        message = it.message,
                        date = it.date,
                    )
                }.toImmutableList(),
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HistoryUiState(),
        )
}
