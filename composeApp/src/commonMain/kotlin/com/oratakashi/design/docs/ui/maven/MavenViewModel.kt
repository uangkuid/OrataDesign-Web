package com.oratakashi.design.docs.ui.maven

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oratakashi.design.docs.domain.model.MavenMetadata
import com.oratakashi.design.docs.domain.usecase.GetMavenMetadataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MavenViewModel(
    private val getMavenMetadataUseCase: GetMavenMetadataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MavenUiState>(MavenUiState.Loading)
    val uiState: StateFlow<MavenUiState> = _uiState.asStateFlow()

    init {
        fetchMavenMetadata()
    }

    fun fetchMavenMetadata() {
        viewModelScope.launch {
            _uiState.value = MavenUiState.Loading
            getMavenMetadataUseCase().fold(
                onSuccess = { metadata ->
                    _uiState.value = MavenUiState.Success(metadata)
                },
                onFailure = { error ->
                    _uiState.value = MavenUiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}

sealed class MavenUiState {
    data object Loading : MavenUiState()
    data class Success(val metadata: MavenMetadata) : MavenUiState()
    data class Error(val message: String) : MavenUiState()
}

