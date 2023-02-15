package com.miguelaboliveira.svpolitician.feature.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelaboliveira.svpolitician.feature.settings.domain.GetSyncIntervalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
public class SettingsViewModel @Inject constructor(
    getSyncIntervalUseCase: GetSyncIntervalUseCase
) : ViewModel() {

    public val uiState: StateFlow<SettingsUiState> = getSyncIntervalUseCase()
        .map {
            SettingsUiState(
                syncInterval = Duration.ofMinutes(it),
                version = "TODO FROM SOMEWHERE"
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SettingsUiState(
                syncInterval = Duration.ZERO,
                version = ""
            )
        )
}
