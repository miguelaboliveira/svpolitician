package com.miguelaboliveira.svpolitician.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miguelaboliveira.svpolitician.ui.preview.FullPreviews
import com.miguelaboliveira.svpolitician.ui.theme.SVPoliticianTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun HomeScreen(
    state: HomeUiState,
    modifier: Modifier = Modifier,
    onFetchClicked: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Home") }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (state.loading) {
                Loading(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (state.phrase != null) {
                val formattedDate = remember(state.phrase.date) {
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(state.phrase.date.atZone(ZoneId.systemDefault()))
                }
                Phrase(
                    modifier = Modifier.fillMaxSize(),
                    phrase = state.phrase.message,
                    date = formattedDate,
                    onFetchClicked = onFetchClicked
                )
            }
        }
    }
}

@Composable
private fun Loading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier
    )
}

@Composable
private fun Phrase(
    phrase: String,
    date: String,
    modifier: Modifier = Modifier,
    onFetchClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth(),
            text = phrase,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = date,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Button(onClick = onFetchClicked) {
            Text(text = "Get a new one")
        }
    }
}

@FullPreviews
@Composable
private fun HomeScreenPreview() {
    SVPoliticianTheme {
        HomeScreen(
            state = HomeUiState(
                loading = false,
                refreshing = false,
                phrase = HomeUiState.Phrase(
                    message = "Some random phrase",
                    date = Instant.now()
                )
            ),
            onFetchClicked = {}
        )
    }
}
