package com.matabel.feature.status

import com.matabel.core.model.BtDevice

// TOTO Should be in ui?
data class StatusUiState(
    val btDevices: List<BtDevice>,
)