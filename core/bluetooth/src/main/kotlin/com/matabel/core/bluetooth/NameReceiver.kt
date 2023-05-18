package com.matabel.core.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlin.reflect.KFunction1

class NameReceiver(
    private val context: Context,
    private val onNameChange: (Name) -> Unit,
) : BroadcastReceiver() {

    data class Name(
        val device: BluetoothDevice,
        val transport: Int,
    )

    @SuppressLint("InlinedApi")
    override fun onReceive(context: Context, intent: Intent) {
        val device =
            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)!!
//        val transport = intent.getIntExtra(BluetoothDevice.EXTRA_TRANSPORT, BluetoothAdapter.ERROR)
//
//        onNameChange(Acl(device, transport))
    }

    fun unregister() {
        context.unregisterReceiver(this)
    }

    companion object {
        fun Context.registerNameReceiver(onNameChange: KFunction1<Name, Unit>) {
            val filter = IntentFilter(BluetoothDevice.ACTION_NAME_CHANGED)
            registerReceiver(NameReceiver(this, onNameChange), filter)
        }
    }
}