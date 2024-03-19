package com.miguelaboliveira.svpolitician.ui.error

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.miguelaboliveira.svpolitician.core.ui.error.R

@Composable
public fun ErrorHandler(
    snackBarHostState: SnackbarHostState,
    uiError: UiError?,
    onErrorConsumed: (Long) -> Unit,
) {
    val context = LocalContext.current
    val latestOnErrorConsumed by rememberUpdatedState(onErrorConsumed)
    uiError?.let {
        val message =
            when (uiError.type) {
                UiError.Type.NO_CONNECTION ->
                    context.getString(
                        R.string.error_no_internet_connection,
                    )

                UiError.Type.HTTP -> context.getString(R.string.error_http)
                else -> context.getString(R.string.error_unknown)
            }
        LaunchedEffect(snackBarHostState, uiError.id) {
            snackBarHostState.showSnackbar(
                message = message,
                actionLabel = context.getString(R.string.error_dismiss),
            )
            latestOnErrorConsumed(uiError.id)
        }
    }
}
