package com.matabel.core.database

import com.matabel.core.database.dao.BtDeviceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun provideBtDeviceDao(database: LeftBehindDatabase): BtDeviceDao =
        database.btDeviceDao()
}