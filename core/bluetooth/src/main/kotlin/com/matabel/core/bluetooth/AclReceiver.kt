package com.matabel.core.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlin.reflect.KFunction1

class AclReceiver(
    private val context: Context,
    private val onAcl: (Acl) -> Unit,
) : BroadcastReceiver() {

    data class Acl(
        val device: BluetoothDevice,
        val transport: Int,
    )

    @SuppressLint("InlinedApi")
    override fun onReceive(context: Context, intent: Intent) {
        val device =
            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)!!
        val transport = intent.getIntExtra(BluetoothDevice.EXTRA_TRANSPORT, BluetoothAdapter.ERROR)

        onAcl(Acl(device, transport))
    }

    fun unregister() {
        context.unregisterReceiver(this)
    }

    companion object {
        fun Context.registerAclReceiver(onAcl: KFunction1<Acl, Unit>) {
            val filter = IntentFilter().apply {
                addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
                addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
                addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED) // Not tested
            }
            registerReceiver(AclReceiver(this, onAcl), filter)
        }
    }
}