package com.matabel.sync.work.old

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import com.matabel.core.data.repository.BtDeviceRepository
import com.matabel.core.model.BtDevice
import com.matabel.core.model.BtDeviceSettings
import com.matabel.sync.work.BluetoothBaseWorker
import com.matabel.sync.work.delegate.DelegatingWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltWorker
class SyncBondedDevicesWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val btDeviceRepository: BtDeviceRepository,
) : BluetoothBaseWorker(appContext, workerParams) {
    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        Timber.e("doWork")
        return suspendCoroutine { continuation ->
            // TODO: Get devices from system bluetooth and settings from repo. so you have stuff to
            // reference with the address from the onChanged methods
            val canUseBluetooth = canUseBluetooth()
            if (canUseBluetooth.isSuccess()) {
                val bluetoothManager: BluetoothManager =
                    appContext.getSystemService(BluetoothManager::class.java)

                val btAdapter = bluetoothManager.adapter
                val bondedDevices = btAdapter.bondedDevices
                btDeviceRepository.insertAll(bondedDevices.map { it.asDataModel() })
                continuation.resume(Result.success())
            } else {
                continuation.resume(Result.failure())
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun BluetoothDevice.asDataModel(): BtDevice {
        return BtDevice(
            address,
            name,
            null,
            BtDeviceSettings(canObserve = false)
        )
    }

    companion object {
        /**
         * Expedited one time work to sync data on app startup
         */
        /**
         * Expedited one time work to sync data on app startup
         */
        fun createWorkRequest() = DelegatingWorker.createWorkRequest(
            getConstraints(),
            SyncBondedDevicesWorker::class,
        )

        private fun getConstraints() =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
    }
}