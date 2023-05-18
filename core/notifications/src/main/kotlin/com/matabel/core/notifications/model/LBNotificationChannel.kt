package com.matabel.core.notifications.model

enum class LBNotificationChannel(
    val channelId: String,
    val channelName: String,
    val groupId: String,
) {
    GENERAL("channel_general", "General Channel", LBNotificationGroups.GENERAL.groupId),
    DEVICES("channel_device", "Device Channel", LBNotificationGroups.DEVICES.groupId),
}