package com.matabel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matabel.core.database.dao.BtDeviceDao
import com.matabel.core.database.model.BtDeviceEntity

@Database(
    entities = [
        BtDeviceEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class LeftBehindDatabase : RoomDatabase() {
    abstract fun btDeviceDao(): BtDeviceDao
}