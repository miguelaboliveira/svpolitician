package com.miguelaboliveira.svpolitician.core.ephemeral

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

public interface EphemeralStore<S> {
    public val state: StateFlow<S>
    public fun update(transform: (state: S) -> S)
}

public fun <S> ephemeralStore(
    initialState: S,
    readSavedState: () -> String?,
    writeSavedState: (String) -> Unit,
    encode: (S) -> String,
    decode: (String) -> S,
): EphemeralStore<S> = EphemeralStoreImpl(
    initialState = initialState,
    readSavedState = readSavedState,
    writeSavedState = writeSavedState,
    encode = encode,
    decode = decode,
)

private class EphemeralStoreImpl<S>(
    initialState: S,
    readSavedState: () -> String?,
    private val writeSavedState: (String) -> Unit,
    private val encode: (S) -> String,
    decode: (String) -> S,
) : EphemeralStore<S> {
    private val _state: MutableStateFlow<S>

    init {
        val savedState = readSavedState()
        _state = if (savedState == null) {
            MutableStateFlow(initialState)
        } else {
            MutableStateFlow(decode(savedState))
        }
    }

    override val state: StateFlow<S> = _state.asStateFlow()

    override fun update(transform: (S) -> S) {
        writeSavedState(encode(_state.updateAndGet(transform)))
    }
}
