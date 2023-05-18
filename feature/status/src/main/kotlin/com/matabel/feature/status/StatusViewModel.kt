package com.matabel.feature.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matabel.core.data.repository.BtDeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StatusViewModel @Inject constructor(
    btDeviceRepository: BtDeviceRepository
) : ViewModel() {

    val uiState: StateFlow<StatusUiState> =
        btDeviceRepository.observeAllBtDevices()
            .map { StatusUiState(it) }
            .onStart { StatusUiState(emptyList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = StatusUiState(emptyList()),
            )
}