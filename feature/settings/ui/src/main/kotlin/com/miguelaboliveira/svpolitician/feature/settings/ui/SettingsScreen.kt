package com.miguelaboliveira.svpolitician.feature.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miguelaboliveira.svpolitician.ui.preview.FullPreviews
import com.miguelaboliveira.svpolitician.ui.theme.SVPoliticianTheme
import java.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun SettingsScreen(
    state: SettingsUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Settings") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Getting new phrase every ${state.syncInterval.toMinutes()}")
            Spacer(modifier = Modifier.weight(1F))
            Text(text = state.version)
        }
    }
}

@FullPreviews
@Composable
private fun SettingsScreenPreview() {
    SVPoliticianTheme {
        SettingsScreen(
            state = SettingsUiState(
                syncInterval = Duration.ofMinutes(15),
                version = "1.0.0"
            )
        )
    }
}
