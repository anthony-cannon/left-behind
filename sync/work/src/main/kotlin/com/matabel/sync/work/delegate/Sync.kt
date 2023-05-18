package com.matabel.sync.work.delegate

import android.content.Context
import androidx.work.WorkManager
import com.matabel.sync.work.intent.BluetoothIntentWorker.Companion.enqueueBluetoothIntentWorker

object Sync {
    // This method is initializes sync, the process that keeps the app's data current.
    // It is called from the app module's Application.onCreate() and should be only done once.
    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            // Run sync on app startup and ensure only one sync worker runs at any time
//            enqueueBluetoothBackgroundWorker()
            enqueueBluetoothIntentWorker()
        }
    }
}