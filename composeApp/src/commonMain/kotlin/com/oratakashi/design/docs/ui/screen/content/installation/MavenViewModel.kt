package com.oratakashi.design.docs.ui.screen.content.installation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oratakashi.design.docs.data.model.state.State
import com.oratakashi.design.docs.domain.usecase.GetMavenMetadataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MavenViewModel(
    private val getMavenMetadataUseCase: GetMavenMetadataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    init {
        fetchMavenMetadata()
    }

    fun fetchMavenMetadata() {
        viewModelScope.launch {
            println("MavenViewModel: Starting to fetch maven metadata...")
            _uiState.value = State.Loading

            getMavenMetadataUseCase().fold(
                onSuccess = { metadata ->
                    _uiState.value = State.Success(metadata)
                },
                onFailure = { error ->
                    error.printStackTrace()
                    _uiState.value = State.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}