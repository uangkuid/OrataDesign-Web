package com.oratakashi.design.docs.data.model.state

sealed class State {
    data object Loading : State()
    data class Success<T>(val data: T) : State()
    data class Error(val message: String) : State()
}