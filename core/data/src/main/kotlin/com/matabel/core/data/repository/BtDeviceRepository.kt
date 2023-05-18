package com.matabel.core.data.repository

import com.matabel.core.model.BtDevice
import kotlinx.coroutines.flow.Flow

interface BtDeviceRepository {
    fun observeAllBtDevices(): Flow<List<BtDevice>>
    fun insertAll(btDevices: List<BtDevice>)
}