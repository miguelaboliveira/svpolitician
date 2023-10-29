package com.miguelaboliveira.svpolitician.ui.fragmentext

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.miguelaboliveira.svpolitician.core.ui.design.SVPoliticianTheme

public fun Fragment.svPoliticianComposeView(content: @Composable () -> Unit): ComposeView =
    ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            SVPoliticianTheme(content = content)
        }
    }
