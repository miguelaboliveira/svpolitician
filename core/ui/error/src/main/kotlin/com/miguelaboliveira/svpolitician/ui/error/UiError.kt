package com.miguelaboliveira.svpolitician.ui.error

public data class UiError(
    val id: Long,
    val type: Type,
) {
    public enum class Type {
        NO_CONNECTION,
        HTTP,
        UNKNOWN,
    }
}
