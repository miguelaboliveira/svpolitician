package com.miguelaboliveira.svpolitician.ui.error

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.res.stringResource
import com.miguelaboliveira.svpolitician.core.ui.error.R

@Composable
public fun ErrorHandler(
    snackBarHostState: SnackbarHostState,
    uiError: UiError?,
    onErrorConsumed: (Long) -> Unit,
) {
    val latestOnErrorConsumed by rememberUpdatedState(onErrorConsumed)
    uiError?.let {
        val message =
            when (uiError.type) {
                UiError.Type.NO_CONNECTION -> {
                    stringResource(
                        R.string.error_no_internet_connection,
                    )
                }

                UiError.Type.HTTP -> {
                    stringResource(R.string.error_http)
                }

                else -> {
                    stringResource(R.string.error_unknown)
                }
            }
        val label = stringResource(R.string.error_dismiss)
        LaunchedEffect(snackBarHostState, uiError.id) {
            snackBarHostState.showSnackbar(
                message = message,
                actionLabel = label,
            )
            latestOnErrorConsumed(uiError.id)
        }
    }
}
