package com.matabel.myapplication.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

sealed class Permission(val permission: String, val rationale: String, val minApi: Int) {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    object NotificationsPost : Permission(
        Manifest.permission.POST_NOTIFICATIONS,
        "We need notifications so we can notify you",
        Build.VERSION_CODES.TIRAMISU,
    )

    object BluetoothAdmin : Permission(
        Manifest.permission.BLUETOOTH_ADMIN,
        "We need bluetooth so we can scan for devices",
        Build.VERSION_CODES.BASE,
    )

    object Bluetooth : Permission(
        Manifest.permission.BLUETOOTH,
        "We need bluetooth so we can connect to devices",
        Build.VERSION_CODES.BASE,
    )

    @RequiresApi(Build.VERSION_CODES.S)
    object BluetoothConnect : Permission(
        Manifest.permission.BLUETOOTH_CONNECT,
        "We need bluetooth so we can connect to devices",
        Build.VERSION_CODES.S,
    )

    @RequiresApi(Build.VERSION_CODES.S)
    object BluetoothScan : Permission(
        Manifest.permission.BLUETOOTH_SCAN,
        "We need bluetooth so we can connect to devices",
        Build.VERSION_CODES.S,
    )

    object LocationFine : Permission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        "We need location so we know what devices are around you",
        Build.VERSION_CODES.BASE,
    )

    object LocationCoarse : Permission(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        "We need location so we know what devices are around you",
        Build.VERSION_CODES.BASE,
    )
}