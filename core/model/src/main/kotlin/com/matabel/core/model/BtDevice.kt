package com.matabel.core.model

data class BtDevice(
    val address: String,
    val name: String,
    val lastLocation: BtDeviceLocation?,
    val settings: BtDeviceSettings,
)

data class BtDeviceLocation(
    val latitude: Long,
    val longitude: Long,
    val time: Long,
)

data class BtDeviceSettings(
    val canObserve: Boolean,
)
