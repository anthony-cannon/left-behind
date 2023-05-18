package com.matabel.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bt_device")
data class BtDeviceEntity(
    @PrimaryKey
    val address: String,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded(prefix = "last_loc_")
    val lastLocation: BtDeviceLocationEntity?,
    @Embedded(prefix = "settings_")
    val settings: BtDeviceSettingsEntity,
)

data class BtDeviceLocationEntity(
    @ColumnInfo(name = "latitude")
    val latitude: Long,
    @ColumnInfo(name = "longitude")
    val longitude: Long,
    @ColumnInfo(name = "time")
    val time: Long,
)

data class BtDeviceSettingsEntity(
    @ColumnInfo(name = "can_observe")
    val canObserve: Boolean,
)
