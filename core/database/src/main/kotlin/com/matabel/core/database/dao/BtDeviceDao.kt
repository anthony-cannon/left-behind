package com.matabel.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.matabel.core.database.model.BtDeviceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BtDeviceDao {
    @Query("SELECT * FROM bt_device")
    fun getAllBtDevices(): Flow<List<BtDeviceEntity>>

    @Insert
    fun insertAll(btDevices: List<BtDeviceEntity>)

    @Delete
    fun delete(btDevice: BtDeviceEntity)
}