package com.miguelaboliveira.svpolitician.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelaboliveira.svpolitician.feature.home.domain.FetchPhraseUseCase
import com.miguelaboliveira.svpolitician.feature.home.domain.GetMostRecentPhraseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class HomeViewModel @Inject constructor(
    getMostRecentPhraseUseCase: GetMostRecentPhraseUseCase,
    private val fetchPhraseUseCase: FetchPhraseUseCase
) : ViewModel() {

    private val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val refreshState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    public val uiState: StateFlow<HomeUiState> = combine(
        loadingState,
        refreshState,
        getMostRecentPhraseUseCase()
    ) { loading, refreshing, mostRecentPhrase ->
        HomeUiState(
            loading = loading,
            refreshing = refreshing,
            phrase = HomeUiState.Phrase(mostRecentPhrase.message, mostRecentPhrase.date)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(
            loading = true,
            refreshing = false,
            phrase = null
        )
    )

    init {
        viewModelScope.launch {
            fetchPhraseUseCase(loadingState)
        }
    }

    public fun refresh() {
        viewModelScope.launch {
            fetchPhraseUseCase(refreshState)
        }
    }
}
