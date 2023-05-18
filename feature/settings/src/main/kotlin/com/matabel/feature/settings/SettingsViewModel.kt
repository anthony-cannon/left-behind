package com.matabel.feature.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
//    private val userDataRepository: UserDataRepository,
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> = MutableStateFlow(SettingsUiState.Loading)
//        userDataRepository.userData
//            .map { userData ->
//                Success(
//                    settings = UserEditableSettings(
//                        brand = userData.themeBrand,
//                        useDynamicColor = userData.useDynamicColor,
//                        darkThemeConfig = userData.darkThemeConfig,
//                    ),
//                )
//            }
//            .stateIn(
//                scope = viewModelScope,
//                // Starting eagerly means the user data is ready when the SettingsDialog is laid out
//                // for the first time. Without this, due to b/221643630 the layout is done using the
//                // "Loading" text, then replaced with the user editable fields once loaded, however,
//                // the layout height doesn't change meaning all the fields are squashed into a small
//                // scrollable column.
//                // TODO: Change to SharingStarted.WhileSubscribed(5_000) when b/221643630 is fixed
//                started = SharingStarted.Eagerly,
//                initialValue = Loading,
//            )
//
//    fun updateThemeBrand(themeBrand: ThemeBrand) {
//        viewModelScope.launch {
//            userDataRepository.setThemeBrand(themeBrand)
//        }
//    }
//
//    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
//        viewModelScope.launch {
//            userDataRepository.setDarkThemeConfig(darkThemeConfig)
//        }
//    }
//
//    fun updateDynamicColorPreference(useDynamicColor: Boolean) {
//        viewModelScope.launch {
//            userDataRepository.setDynamicColorPreference(useDynamicColor)
//        }
//    }
}

///**
// * Represents the settings which the user can edit within the app.
// */
//data class UserEditableSettings(
//    val brand: ThemeBrand,
//    val useDynamicColor: Boolean,
//    val darkThemeConfig: DarkThemeConfig,
//)

