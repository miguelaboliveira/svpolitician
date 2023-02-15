package com.miguelaboliveira.svpolitician.feature.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miguelaboliveira.svpolitician.ui.theme.SVPoliticianTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public class HistoryFragment : Fragment() {

    private val viewModel by viewModels<HistoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
            .apply {
                setViewCompositionStrategy(
                    ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                )
                setContent {
                    SVPoliticianTheme {
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                        HistoryScreen(
                            state = uiState
                        )
                    }
                }
            }
    }
}
