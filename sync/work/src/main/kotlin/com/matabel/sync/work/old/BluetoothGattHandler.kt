package com.matabel.sync.work.old

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothProfile
import android.content.Context
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap

/**
 * Connection
 * Best     -30   and above
 * Strong   -30.1 to -55
 * Good     -55.1 to -67
 * Bad      -67.1 to -80
 * Terrible -80.1 to -90
 * Unusable -90.1 and below
 */
@SuppressLint("MissingPermission")
class BluetoothGattHandler(private val callback: () -> Unit) : BluetoothGattCallback() {

    private val deviceGattMap = ConcurrentHashMap<BluetoothDevice, BluetoothGatt>()

    fun connectToDevice(context: Context, device: BluetoothDevice) {
        Timber.e("connectToDevice: connecting to ${device.address}")
        val gatt = device.connectGatt(context, false, this, BluetoothDevice.TRANSPORT_LE)
        gatt.connect()
    }

    override fun onConnectionStateChange(
        gatt: BluetoothGatt,
        status: Int,
        newState: Int
    ) {
        val deviceAddress = gatt.device.address

        if (status == BluetoothGatt.GATT_SUCCESS) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Timber.e("onConnectionStateChange: connected to $deviceAddress")
                deviceGattMap[gatt.device] = gatt
//                requestRssi(gatt)
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Timber.e("onConnectionStateChange: disconnected from $deviceAddress")
                teardownConnection(gatt.device)
                callback()
            }
        } else {
            Timber.e("onConnectionStateChange: status $status encountered for $deviceAddress!")
            teardownConnection(gatt.device)
        }
    }

//    private fun requestRssi(gatt: BluetoothGatt) {
//        Handler(Looper.getMainLooper()).postDelayed({
//            gatt.readRemoteRssi()
//            requestRssi(gatt)
//        }, 5000)
//    }

//    override fun onReadRemoteRssi(gatt: BluetoothGatt, rssi: Int, status: Int) {
//        Timber.e("onReadRemoteRssi: rssi for ${gatt.device.address} is $rssi")
//    }

    fun teardownConnection(device: BluetoothDevice) {
        if (device.isConnected()) {
            deviceGattMap[device]?.close()
            deviceGattMap.remove(device)
            Timber.e("teardownConnection: connection for ${device.address} has been teardown!")
        } else {
            Timber.e("teardownConnection: not connected to ${device.address}, cannot teardown connection!")
        }
    }

    fun onStop() {
        deviceGattMap.forEach {
            teardownConnection(it.key)
        }
    }

    private fun BluetoothDevice.isConnected() = deviceGattMap.containsKey(this)
}