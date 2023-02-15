package com.miguelaboliveira.svpolitician.feature.history.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlinx.collections.immutable.ImmutableList
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun HistoryScreen(
    state: HistoryUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "History") }
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
            } else {
                if (state.phrases.isEmpty()) {
                    Empty(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    PhrasesList(phrases = state.phrases)
                }
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
private fun Empty(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "No phrases yet."
    )
}

@Composable
private fun PhrasesList(
    phrases: ImmutableList<HistoryUiState.Phrase>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(phrases, { it.id }) {
            val formattedDate = remember(it.id, it.date) {
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .format(it.date.atZone(ZoneId.systemDefault()))
            }
            Phrase(
                modifier = Modifier.fillMaxWidth(),
                phrase = it.message,
                date = formattedDate
            )
        }
    }
}

@Composable
private fun Phrase(
    phrase: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = phrase,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = date,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@FullPreviews
@Composable
private fun HistoryScreenPreview() {
    SVPoliticianTheme {
        // TODO
    }
}
