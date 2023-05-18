package com.matabel.sync.work.old

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.matabel.core.data.repository.BtDeviceRepository
import com.matabel.core.model.BtDevice
import com.matabel.sync.work.BluetoothBaseWorker
import com.matabel.sync.work.delegate.DelegatingWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class BluetoothBackgroundWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val btDeviceRepository: BtDeviceRepository,
) : BluetoothBaseWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext canUseBluetooth()
//                .onSuccess {
//                    // listen to device changes in bluetooth device management using intents
//                    // listen to devices in DB, depending on settings, listen or not
//                    btDeviceRepository
//                        .observeAllBtDevices()
//                        .collect { btDevices ->
//                            observeDevices(btDevices)
//                        }
//                    Result.success()
//                }
//                .onFailure {
//                    return@onFailure it
//                }
        }
    }

    private fun observeDevices(btDevices: List<BtDevice>) {
        val gattCallback = BluetoothGattHandler {
//                with(NotificationManagerCompat.from(appContext)) {
//                    notify(
//                        1,
//                        com.matabel.core.notifications.TestNotification()
//                            .create(appContext, "Watch Left Behind!", "")
//                    )
//                }
        }
        btDevices
            .filter { it.settings.canObserve }
            .forEach { btDevice ->
//                gattCallback.connectToDevice(applicationContext, btDevice)
            }
    }

    companion object {

        private const val uniqueWorkName = "BluetoothBackgroundWorker"

        /**
         * Expedited one time work to sync data on app startup
         */
        private fun createWorkRequest() = DelegatingWorker.createWorkRequest(
            getConstraints(),
            BluetoothBackgroundWorker::class,
        )

        private fun getConstraints() =
            Constraints.Builder()
                .build()


        fun WorkManager.enqueueBluetoothBackgroundWorker() {
            enqueueUniqueWork(
                uniqueWorkName,
                ExistingWorkPolicy.KEEP,
                createWorkRequest(),
            )
        }
    }
}