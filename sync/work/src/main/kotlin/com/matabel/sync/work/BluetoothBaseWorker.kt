package com.matabel.sync.work

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import timber.log.Timber

abstract class BluetoothBaseWorker(
    private val appContext: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    @SuppressLint("InlinedApi")
    private val permissions = listOf(
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    fun canUseBluetooth(): Result {
        val bluetoothManager: BluetoothManager? =
            appContext.getSystemService(BluetoothManager::class.java)

        if (bluetoothManager == null) {
            Timber.e("Device doesn't support Bluetooth")
            return Result.failure()
        }

        val hasAllPermissions = permissions
            .map { permission ->
                ContextCompat.checkSelfPermission(
                    appContext,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
            .all { it }

        if (!hasAllPermissions) {
            Timber.e("Request BT permissions")
            return Result.failure()
        }

        val btAdapter = bluetoothManager.adapter

        if (!btAdapter.isEnabled) {
            Timber.e("Request BT to be enabled")
            // Could potentially turn on with bluetooth admin
            return Result.failure()
        }

        return Result.success()
    }

    fun Result.isSuccess(): Boolean {
        return this is Result.Success
    }

    suspend fun Result.onSuccess(callback: suspend (result: Result.Success) -> Result): Result {
        if (this is Result.Success) {
            callback(this)
        }
        return this
    }

    suspend fun Result.onFailure(callback: suspend (result: Result.Failure) -> Result): Result {
        if (this is Result.Failure) {
            callback(this)
        }
        return this
    }
}