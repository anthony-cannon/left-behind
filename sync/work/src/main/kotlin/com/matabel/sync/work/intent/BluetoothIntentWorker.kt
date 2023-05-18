package com.matabel.sync.work.intent

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.matabel.core.bluetooth.AclReceiver
import com.matabel.core.bluetooth.AclReceiver.Companion.registerAclReceiver
import com.matabel.core.bluetooth.ConnectionStateReceiver
import com.matabel.core.bluetooth.ConnectionStateReceiver.Companion.registerConnectionStateReceiver
import com.matabel.core.bluetooth.FoundReceiver
import com.matabel.core.bluetooth.FoundReceiver.Companion.registerFoundReceiver
import com.matabel.core.bluetooth.NameReceiver
import com.matabel.core.bluetooth.NameReceiver.Companion.registerNameReceiver
import com.matabel.core.bluetooth.StateReceiver
import com.matabel.core.bluetooth.StateReceiver.Companion.registerStateReceiver
import com.matabel.core.data.repository.BtDeviceRepository
import com.matabel.core.notifications.NotificationBuilder
import com.matabel.core.notifications.model.LBNotificationType
import com.matabel.sync.work.BluetoothBaseWorker
import com.matabel.sync.work.delegate.DelegatingWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber
import kotlin.coroutines.suspendCoroutine

@HiltWorker
class BluetoothIntentWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val notification: NotificationBuilder,
    private val btDeviceRepository: BtDeviceRepository,
) : BluetoothBaseWorker(appContext, workerParams) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        Timber.e("doWork")
        return suspendCoroutine {
            // TODO: Get devices from system bluetooth and settings from repo. so you have stuff to
            // reference with the address from the onChanged methods
            val canUseBluetooth = canUseBluetooth()
            if (canUseBluetooth.isSuccess()) {
                registerReceivers()

                val bluetoothManager: BluetoothManager =
                    appContext.getSystemService(BluetoothManager::class.java)
                val btAdapter = bluetoothManager.adapter

//                val dbDevices = btDeviceRepository.observeAllBtDevices()
//                val sysDevices = btAdapter.bondedDevices

//                Handler(Looper.getMainLooper()).postDelayed(
//                    {
//                        btAdapter.startDiscovery()
//                        Timber.e("startDiscovery")
//                    },
//                    1000
//                )
            } else {
                notification.show(appContext, LBNotificationType.BluetoothPermissions)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun registerReceivers() {
        appContext.registerStateReceiver(::onStateChanged)
        appContext.registerConnectionStateReceiver(::onConnectionStateChanged)
        appContext.registerFoundReceiver(::onDeviceFound)
        appContext.registerAclReceiver(::onAcl)
        appContext.registerNameReceiver(::onNameChange)
    }

    private fun onStateChanged(state: StateReceiver.State) {
        Timber.e("onStateChanged")
        Timber.e(state.toString())
        when (state.state) {
            BluetoothAdapter.STATE_ON -> {
                notification.cancel(appContext, LBNotificationType.BluetoothStatus)
            }

            BluetoothAdapter.STATE_OFF -> {
                notification.show(appContext, LBNotificationType.BluetoothStatus)
            }
        }
    }

    private fun onConnectionStateChanged(state: ConnectionStateReceiver.ConnectionState) {
        Timber.e("onConnectionStateChanged")
        Timber.e(state.toString())
        when (state.connectionState) {
            BluetoothAdapter.STATE_CONNECTED -> {
                notification.cancel(appContext, LBNotificationType.DeviceStatus)
            }

            BluetoothAdapter.STATE_DISCONNECTED -> {
                notification.show(appContext, LBNotificationType.DeviceStatus)
            }
        }
    }

    private fun onDeviceFound(found: FoundReceiver.Found) {
        Timber.e("onDeviceFound")
        Timber.e(found.toString())
    }

    private fun onAcl(acl: AclReceiver.Acl) {
        Timber.e("onAcl")
        Timber.e(acl.toString())
    }

    private fun onNameChange(name: NameReceiver.Name) {
        Timber.e("onNameChange")
        Timber.e(name.toString())
    }

    companion object {

        private const val uniqueWorkName = "BluetoothIntentWorker"

        /**
         * Expedited one time work to sync data on app startup
         */
        private fun createWorkRequest() = DelegatingWorker.createWorkRequest(
            getConstraints(),
            BluetoothIntentWorker::class,
        )

        private fun getConstraints() =
            Constraints.Builder()
                .build()

        fun WorkManager.enqueueBluetoothIntentWorker() {
            enqueueUniqueWork(
                uniqueWorkName,
                ExistingWorkPolicy.REPLACE,
                createWorkRequest(),
            )
        }
    }
}