package com.miguelaboliveira.svpolitician.feature.history.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelaboliveira.svpolitician.core.ui.design.SVPoliticianTheme
import com.miguelaboliveira.svpolitician.core.ui.design.preview.PreviewFull
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun HistoryScreen(
    state: HistoryUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = LocalContext.current.getString(R.string.history_top_bar_title))
                },
            )
        },
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        start = it.calculateStartPadding(LocalLayoutDirection.current),
                        end = it.calculateEndPadding(LocalLayoutDirection.current),
                    ),
        ) {
            when {
                state.loading -> {
                    Loading(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                state.phrases.isEmpty() -> {
                    Empty(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                else -> {
                    PhrasesList(
                        modifier = Modifier.fillMaxSize(),
                        phrases = state.phrases,
                    )
                }
            }
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
    )
}

@Composable
private fun Empty(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = LocalContext.current.getString(R.string.history_no_phrase),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun PhrasesList(
    phrases: ImmutableList<HistoryUiState.Phrase>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.animateContentSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(phrases, { it.id }) {
            val formattedDate =
                remember(it.id, it.date) {
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(it.date.atZone(ZoneId.systemDefault()))
                }
            Phrase(
                modifier = Modifier.fillMaxWidth(),
                phrase = it.message,
                date = formattedDate,
            )
        }
    }
}

@Composable
private fun Phrase(
    phrase: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = phrase,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = date,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview
@Composable
private fun HistoryScreenLoadingPreview() {
    SVPoliticianTheme {
        HistoryScreen(state = HistoryUiState())
    }
}

@Preview
@Composable
private fun HistoryScreenEmptyPreview() {
    SVPoliticianTheme {
        HistoryScreen(
            state =
                HistoryUiState(
                    loading = false,
                ),
        )
    }
}

@PreviewFull
@Composable
private fun HistoryScreenPreview() {
    SVPoliticianTheme {
        HistoryScreen(
            state =
                HistoryUiState(
                    loading = false,
                    phrases =
                        persistentListOf(
                            HistoryUiState.Phrase(
                                id = 0,
                                message = "Some random phrase",
                                date = Instant.now(),
                            ),
                            HistoryUiState.Phrase(
                                id = 1,
                                message = "Another random phrase",
                                date = Instant.now(),
                            ),
                        ),
                ),
        )
    }
}
