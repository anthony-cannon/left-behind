package com.matabel.sync.work.old

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import timber.log.Timber
import kotlin.coroutines.suspendCoroutine

@SuppressLint("MissingPermission")
class TestWorker(
    private val appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return suspendCoroutine { continuation ->
            Timber.e("doWork")

            val bluetoothManager: BluetoothManager =
                appContext.getSystemService(BluetoothManager::class.java)
            val btAdapter = bluetoothManager.adapter

            if (btAdapter == null) {
                Timber.e("Device doesn't support Bluetooth")
                continuation.resumeWith(kotlin.Result.success(Result.failure()))
                return@suspendCoroutine
            }

            if (ContextCompat.checkSelfPermission(
                    appContext,
                    android.Manifest.permission.BLUETOOTH_SCAN
                ) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(
                    appContext,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_DENIED
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    Timber.e("Request BT permissions")
                    continuation.resumeWith(kotlin.Result.success(Result.failure()))
                    return@suspendCoroutine
                } else {
                    Timber.e("Request BT to be enabled")
                    continuation.resumeWith(kotlin.Result.success(Result.failure()))
                    return@suspendCoroutine
                }
            }

            if (!btAdapter.isEnabled) {
                Timber.e("Request BT to be enabled")
                continuation.resumeWith(kotlin.Result.success(Result.failure()))
                return@suspendCoroutine
            }

//            val btScanner = btAdapter.bluetoothLeScanner
//            if (btScanner == null) {
//                Timber.e("No scanner")
//                continuation.resumeWith(kotlin.Result.success(Result.failure()))
//                return@suspendCoroutine
//            }

            val watch = btAdapter.bondedDevices
                ?.find { it.name.contains("watch", ignoreCase = true) }

            if (watch == null) {
                Timber.e("No watch")
                return@suspendCoroutine
            }

            val gattCallback = BluetoothGattHandler {
//                with(NotificationManagerCompat.from(appContext)) {
//                    notify(
//                        1,
//                        com.matabel.core.notifications.TestNotification()
//                            .create(appContext, "Watch Left Behind!", "")
//                    )
//                }
            }
            gattCallback.connectToDevice(applicationContext, watch)
        }
    }
}
