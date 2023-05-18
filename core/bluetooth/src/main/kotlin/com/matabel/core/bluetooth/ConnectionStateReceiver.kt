package com.matabel.core.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class ConnectionStateReceiver(
    private val context: Context,
    private val onConnectionStateChanged: (ConnectionState) -> Unit,
) : BroadcastReceiver() {

    data class ConnectionState(
        val device: BluetoothDevice,
        val connectionState: Int,
        val previousConnectionState: Int,
    )

    @SuppressLint("NewApi")
    override fun onReceive(context: Context, intent: Intent) {
        val device =
            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)!!
        val connectionState = getIntExtra(intent, BluetoothAdapter.EXTRA_CONNECTION_STATE)
        val previousConnectionState =
            getIntExtra(intent, BluetoothAdapter.EXTRA_PREVIOUS_CONNECTION_STATE)

        onConnectionStateChanged(ConnectionState(device, connectionState, previousConnectionState))
    }

    private fun getIntExtra(intent: Intent, name: String): Int {
        return intent.getIntExtra(name, BluetoothAdapter.ERROR)
    }

    fun unregister() {
        context.unregisterReceiver(this)
    }

    companion object {
        fun Context.registerConnectionStateReceiver(onConnectionStateChanged: (ConnectionState) -> Unit) {
            val filter = IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)
            registerReceiver(ConnectionStateReceiver(this, onConnectionStateChanged), filter)
        }
    }
}