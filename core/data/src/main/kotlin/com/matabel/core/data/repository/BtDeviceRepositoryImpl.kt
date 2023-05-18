package com.matabel.core.data.repository

import com.matabel.core.data.asDataModel
import com.matabel.core.data.asEntity
import com.matabel.core.database.dao.BtDeviceDao
import com.matabel.core.database.model.BtDeviceEntity
import com.matabel.core.model.BtDevice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BtDeviceRepositoryImpl @Inject constructor(
    private val btDeviceDao: BtDeviceDao
) : BtDeviceRepository {
    override fun observeAllBtDevices(): Flow<List<BtDevice>> =
        btDeviceDao.getAllBtDevices()
            .map { it.map(BtDeviceEntity::asDataModel) }
            .flowOn(Dispatchers.IO)

    override fun insertAll(btDevices: List<BtDevice>) {
        btDeviceDao.insertAll(btDevices.map { it.asEntity() })
    }
}