package com.matabel.core.data

import com.matabel.core.database.model.BtDeviceEntity
import com.matabel.core.database.model.BtDeviceLocationEntity
import com.matabel.core.database.model.BtDeviceSettingsEntity
import com.matabel.core.model.BtDevice
import com.matabel.core.model.BtDeviceLocation
import com.matabel.core.model.BtDeviceSettings

fun BtDevice.asEntity() =
    BtDeviceEntity(
        address,
        name,
        lastLocation?.asEntity(),
        settings.asEntity()
    )

fun BtDeviceEntity.asDataModel() =
    BtDevice(
        address,
        name,
        lastLocation?.asDataModel(),
        settings.asDataModel()
    )

fun BtDeviceSettings.asEntity() =
    BtDeviceSettingsEntity(
        canObserve
    )

fun BtDeviceSettingsEntity.asDataModel() =
    BtDeviceSettings(
        canObserve
    )

fun BtDeviceLocation.asEntity() =
    BtDeviceLocationEntity(
        latitude,
        longitude,
        time
    )

fun BtDeviceLocationEntity.asDataModel() =
    BtDeviceLocation(
        latitude,
        longitude,
        time
    )