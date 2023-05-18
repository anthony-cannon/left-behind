package com.matabel.myapplication.ui.devices
//
//import android.annotation.SuppressLint
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothManager
//import androidx.activity.ComponentActivity
//import com.matabel.myapplication.data.DeviceEnt
//import com.matabel.myapplication.permission.Permission
//import com.matabel.myapplication.permission.PermissionManager
//import com.matabel.myapplication.permission.PermissionRequester
//import com.matabel.myapplication.ui.permission.PermissionRationaleDialog
//import timber.log.Timber
//
//class DevicesBluetoothUseCase(
//    private val permissionRequester: PermissionRequester,
//    private val navigator: (String) -> Unit
//) {
//
//    fun getBluetoothBondedDevices(
//        activity: ComponentActivity,
//        callback: (bondedDevices: List<DeviceEnt>) -> Unit
//    ) {
//        val bluetoothManager: BluetoothManager =
//            activity.getSystemService(BluetoothManager::class.java)
//        val btAdapter = bluetoothManager.adapter
//
//        if (btAdapter == null) {
//            Timber.e("Device doesn't support Bluetooth")
//            return callback(emptyList())
//        }
//
//        PermissionManager.from(activity, permissionRequester)
//            .request(
//                Permission.LocationFine,
//                Permission.LocationCoarse,
//                Permission.Bluetooth,
//                Permission.BluetoothConnect,
//                Permission.BluetoothScan,
//                Permission.NotificationsPost,
//            )
//            .displayRationale {
//                navigator(PermissionRationaleDialog.route(it.permission, it.rationale))
//            }
//            .checkDetailedPermission { results ->
//                if (results.all { it.granted }) {
//                    continueGetBluetoothBoundDevices(btAdapter, callback)
//                } else {
//                    Timber.e("Cant perform task due to permissions")
//                    callback(emptyList())
//                }
//            }
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun continueGetBluetoothBoundDevices(
//        btAdapter: BluetoothAdapter,
//        callback: (List<DeviceEnt>) -> Unit
//    ) {
//        if (!btAdapter.isEnabled) {
//            Timber.e("Request BT to be enabled")
//            return callback(emptyList())
//        }
//
//        val btScanner = btAdapter.bluetoothLeScanner
//        if (btScanner == null) {
//            Timber.e("No scanner")
//            return callback(emptyList())
//        }
//
//        return callback(btAdapter.bondedDevices.map { DeviceEnt(it.name, it.address, false) })
//    }
//}