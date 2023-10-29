package com.miguelaboliveira.svpolitician.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miguelaboliveira.svpolitician.core.ui.design.SVPoliticianTheme
import com.miguelaboliveira.svpolitician.core.ui.design.preview.PreviewFull
import java.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun SettingsScreen(
    state: SettingsUiState,
    onClearStorageClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = context.getString(R.string.settings_top_bar_title))
                },
            )
        },
        bottomBar = {
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                text =
                    context.getString(
                        R.string.settings_version,
                        state.versionName,
                        state.versionCode,
                    ),
                textAlign = TextAlign.Center,
            )
        },
    ) {
        Column(
            modifier =
                Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column {
                Text(
                    text = context.getString(R.string.settings_periodic_update_header),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text =
                        context.getString(
                            R.string.settings_periodic_update_description,
                            // TODO Format interval
                            state.syncInterval.toMinutes().toString(),
                        ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = context.getString(R.string.settings_stored_data_header),
                    style = MaterialTheme.typography.titleMedium,
                )
                Button(onClick = onClearStorageClicked) {
                    Text(text = context.getString(R.string.settings_stored_data_button))
                }
            }
        }
    }
}

@PreviewFull
@Composable
private fun SettingsScreenPreview() {
    SVPoliticianTheme {
        SettingsScreen(
            state =
                SettingsUiState(
                    syncInterval = Duration.ofMinutes(15),
                    versionName = "1.0.0",
                    versionCode = 0,
                ),
            onClearStorageClicked = {},
        )
    }
}
