package com.matabel.core.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class StateReceiver(
    private val context: Context,
    private val onStateChanged: (State) -> Unit,
) : BroadcastReceiver() {

    data class State(
        val state: Int,
        val previousState: Int,
    )

    override fun onReceive(context: Context, intent: Intent) {
        val state = getIntExtra(intent, BluetoothAdapter.EXTRA_STATE)
        val previousState = getIntExtra(intent, BluetoothAdapter.EXTRA_PREVIOUS_STATE)

        onStateChanged(State(state, previousState))
    }

    private fun getIntExtra(intent: Intent, name: String): Int {
        return intent.getIntExtra(name, BluetoothAdapter.ERROR)
    }

    fun unregister() {
        context.unregisterReceiver(this)
    }

    companion object {
        fun Context.registerStateReceiver(onStateChanged: (State) -> Unit) {
            val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
            registerReceiver(StateReceiver(this, onStateChanged), filter)
        }
    }
}