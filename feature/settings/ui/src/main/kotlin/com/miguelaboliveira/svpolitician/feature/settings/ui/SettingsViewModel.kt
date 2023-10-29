package com.miguelaboliveira.svpolitician.feature.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelaboliveira.svpolitician.feature.settings.domain.ClearStorageUseCase
import com.miguelaboliveira.svpolitician.feature.settings.domain.GetSyncIntervalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Duration
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
public class SettingsViewModel
    @Inject
    constructor(
        getSyncIntervalUseCase: GetSyncIntervalUseCase,
        private val clearStorageUseCase: ClearStorageUseCase,
        @Named("versionName") versionName: String,
        @Named("versionCode") versionCode: Int,
    ) : ViewModel() {
        public val uiState: StateFlow<SettingsUiState> =
            getSyncIntervalUseCase()
                .map {
                    SettingsUiState(
                        syncInterval = Duration.ofMinutes(it),
                        versionName = versionName,
                        versionCode = versionCode,
                    )
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue =
                        SettingsUiState(
                            syncInterval = Duration.ZERO,
                            versionName = versionName,
                            versionCode = versionCode,
                        ),
                )

        public fun clearStorage() {
            viewModelScope.launch {
                clearStorageUseCase()
            }
        }
    }
