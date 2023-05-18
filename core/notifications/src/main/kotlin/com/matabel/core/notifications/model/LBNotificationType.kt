package com.matabel.core.notifications.model

sealed class LBNotificationType(
    val id: Int,
    val channelId: String,
    val title: String,
    val text: String
) {
    object BluetoothPermissions : LBNotificationType(
        344,
        LBNotificationChannel.GENERAL.channelId,
        "No access to bluetooth!",
        "Give LeftBehind access to bluetooth to track your items.",
    )

    object BluetoothStatus : LBNotificationType(
        345,
        LBNotificationChannel.GENERAL.channelId,
        "Bluetooth is disabled!",
        "Turn on Bluetooth to allow LeftBehind to track your items.",
    )

    object DeviceStatus : LBNotificationType(
        346,
        LBNotificationChannel.DEVICES.channelId,
        "Device left behind!",
        "You have left behind your device!",
    )
}