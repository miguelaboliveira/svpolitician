package com.miguelaboliveira.svpolitician.feature.home.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelaboliveira.svpolitician.core.ui.design.SVPoliticianTheme
import com.miguelaboliveira.svpolitician.core.ui.design.preview.PreviewFull
import com.miguelaboliveira.svpolitician.ui.error.ErrorHandler
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun HomeScreen(
    state: HomeUiState,
    onErrorConsumed: (Long) -> Unit,
    onFetchClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    ErrorHandler(snackBarHostState, state.errors.firstOrNull(), onErrorConsumed)
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = LocalContext.current.getString(R.string.home_top_bar_title))
                },
            )
        },
        bottomBar = {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(4.dp),
            ) {
                AnimatedVisibility(visible = state.refreshing) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        },
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
        ) {
            when {
                state.loading -> {
                    Loading(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                state.phrase == null -> {
                    Empty(
                        modifier = Modifier.align(Alignment.Center),
                        onFetchClicked = onFetchClicked,
                    )
                }

                else -> {
                    val formattedDate =
                        remember(state.phrase.date) {
                            DateTimeFormatter
                                .ofLocalizedDateTime(FormatStyle.SHORT)
                                .format(state.phrase.date.atZone(ZoneId.systemDefault()))
                        }
                    Phrase(
                        modifier = Modifier.fillMaxSize(),
                        phrase = state.phrase.message,
                        date = formattedDate,
                        onFetchClicked = onFetchClicked,
                    )
                }
            }
        }
    }
}

@Composable
private fun Empty(
    modifier: Modifier = Modifier,
    onFetchClicked: () -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = LocalContext.current.getString(R.string.home_no_phrase),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
        OutlinedButton(
            onClick = onFetchClicked,
        ) {
            Text(text = LocalContext.current.getString(R.string.home_get_first_button))
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
private fun Phrase(
    phrase: String,
    date: String,
    modifier: Modifier = Modifier,
    onFetchClicked: () -> Unit,
) {
    val textStyle = MaterialTheme.typography.displayLarge
    val textColors: List<Color> =
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.error,
        )

    val currentFontSizePx = with(LocalDensity.current) { textStyle.fontSize.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(tween(2500, easing = LinearEasing)),
    )

    val brush =
        Brush.linearGradient(
            colors = textColors,
            start = Offset(offset, offset),
            end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
            tileMode = TileMode.Mirror,
        )

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .weight(1F)
                    .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedContent(targetState = phrase) { targetPhrase ->
                Text(
                    text = targetPhrase,
                    style =
                        MaterialTheme.typography.displayLarge
                            .copy(brush = brush),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        OutlinedButton(
            border = BorderStroke(2.dp, brush),
            onClick = onFetchClicked,
        ) {
            Text(text = LocalContext.current.getString(R.string.home_get_new_button))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
private fun HomeScreenLoadingPreview() {
    SVPoliticianTheme {
        HomeScreen(
            state = HomeUiState(),
            onErrorConsumed = {},
            onFetchClicked = {},
        )
    }
}

@PreviewFull
@Composable
private fun HomeScreenRefreshingPreview() {
    SVPoliticianTheme {
        HomeScreen(
            state =
                HomeUiState(
                    loading = false,
                    refreshing = true,
                    phrase = null,
                ),
            onErrorConsumed = {},
            onFetchClicked = {},
        )
    }
}

@PreviewFull
@Composable
private fun HomeScreenPreview() {
    SVPoliticianTheme {
        HomeScreen(
            state =
                HomeUiState(
                    loading = false,
                    phrase =
                        HomeUiState.Phrase(
                            message = "Some random phrase",
                            date = Instant.now(),
                        ),
                ),
            onErrorConsumed = {},
            onFetchClicked = {},
        )
    }
}
