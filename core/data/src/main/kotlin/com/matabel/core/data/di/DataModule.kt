package com.matabel.core.data.di

import com.matabel.core.data.repository.BtDeviceRepository
import com.matabel.core.data.repository.BtDeviceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsBtDeviceRepository(
        btDeviceRepository: BtDeviceRepositoryImpl,
    ): BtDeviceRepository
}