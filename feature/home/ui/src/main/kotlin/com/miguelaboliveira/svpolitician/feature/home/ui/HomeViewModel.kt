package com.miguelaboliveira.svpolitician.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelaboliveira.svpolitician.feature.home.domain.FetchPhraseUseCase
import com.miguelaboliveira.svpolitician.feature.home.domain.GetMostRecentPhraseUseCase
import com.miguelaboliveira.svpolitician.ui.error.UiError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
public class HomeViewModel @Inject constructor(
    getMostRecentPhraseUseCase: GetMostRecentPhraseUseCase,
    private val fetchPhraseUseCase: FetchPhraseUseCase,
) : ViewModel() {

    private val refreshState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val errorsState: MutableStateFlow<List<UiError>> = MutableStateFlow(emptyList())

    public val uiState: StateFlow<HomeUiState> = combine(
        errorsState,
        refreshState,
        getMostRecentPhraseUseCase(),
    ) { errors, refreshing, mostRecentPhrase ->
        HomeUiState(
            loading = false,
            refreshing = refreshing,
            phrase = mostRecentPhrase?.let {
                HomeUiState.Phrase(
                    mostRecentPhrase.message,
                    mostRecentPhrase.date,
                )
            },
            errors = errors,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(
            loading = true,
        ),
    )

    init {
        refresh()
    }

    public fun refresh() {
        viewModelScope.launch {
            refreshState.update { true }
            runCatching {
                fetchPhraseUseCase()
            }.onFailure { throwable ->
                errorsState.update {
                    it + UiError(
                        UUID.randomUUID().mostSignificantBits,
                        when (throwable) {
                            is IOException -> UiError.Type.NO_CONNECTION
                            // TODO leaking information from other layers
                            else -> UiError.Type.UNKNOWN
                        },
                    )
                }
            }
            refreshState.update { false }
        }
    }

    public fun consumeError(id: Long) {
        errorsState.update {
            it.filterNot { uiError -> uiError.id == id }
        }
    }
}
