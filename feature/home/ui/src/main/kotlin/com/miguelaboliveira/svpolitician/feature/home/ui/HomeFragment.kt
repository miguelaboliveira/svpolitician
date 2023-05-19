package com.miguelaboliveira.svpolitician.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miguelaboliveira.svpolitician.ui.fragmentext.svPoliticianComposeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = svPoliticianComposeView {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(
            state = uiState,
            onFetchClicked = viewModel::refresh,
            onErrorConsumed = viewModel::consumeError,
        )
    }
}
