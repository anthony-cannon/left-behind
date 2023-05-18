package com.matabel.core.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.util.function.Supplier

class FoundReceiver(
    private val context: Context,
    private val onDeviceFound: (Found) -> Unit,
) : BroadcastReceiver() {

    data class Found(
        val clazz: Supplier<*>,
        val isCoordinatedSetMember: Boolean,
        val device: BluetoothDevice,
        val name: String,
        val rssi: Int,
    )

    @SuppressLint("InlinedApi")
    override fun onReceive(context: Context, intent: Intent) {
        val clazz = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS, Supplier::class.java)!!
        val isCoordinatedSetMember =
            intent.getBooleanExtra(BluetoothDevice.EXTRA_IS_COORDINATED_SET_MEMBER, false)
        val device =
            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)!!
        val name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME) ?: ""
        val rssi = intent.getIntExtra(BluetoothDevice.EXTRA_RSSI, BluetoothAdapter.ERROR)

        onDeviceFound(Found(clazz, isCoordinatedSetMember, device, name, rssi))
    }

    fun unregister() {
        context.unregisterReceiver(this)
    }

    companion object {
        fun Context.registerFoundReceiver(onDeviceFound: (Found) -> Unit) {
            val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
            registerReceiver(FoundReceiver(this, onDeviceFound), filter)
        }
    }
}