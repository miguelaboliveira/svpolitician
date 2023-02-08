package com.miguelaboliveira.svpolitician.domain.presenter

import kotlinx.coroutines.flow.StateFlow

public interface Presenter<S : Any> {
    public val state: StateFlow<S>
}
