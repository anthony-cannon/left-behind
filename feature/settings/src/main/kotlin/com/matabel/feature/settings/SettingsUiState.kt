package com.matabel.feature.settings

sealed interface SettingsUiState {
    object Loading : SettingsUiState
    object Success : SettingsUiState
}