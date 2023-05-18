package com.matabel.core.notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.matabel.core.notifications.model.LBNotificationChannel
import com.matabel.core.notifications.model.LBNotificationGroups
import com.matabel.core.notifications.model.LBNotificationType
import javax.inject.Inject

class NotificationBuilderImpl @Inject constructor() : NotificationBuilder {

    override fun show(context: Context, type: LBNotificationType) {
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(type.id, build(context, type))
        }
    }

    private fun build(context: Context, type: LBNotificationType): Notification {
        createNotificationSettings(context)
        return NotificationCompat.Builder(context, type.channelId)
            .setContentTitle(type.title)
            .setContentText(type.text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_STATUS)
            .setOngoing(true)
            .build()
    }

    override fun cancel(context: Context, type: LBNotificationType) {
        with(NotificationManagerCompat.from(context)) {
            cancel(type.id)
        }
    }

    private fun createNotificationSettings(appContext: Context) {
        val groups = LBNotificationGroups.values()
            .map { NotificationChannelGroup(it.groupId, it.groupName) }

        val channels = LBNotificationChannel.values()
            .map {
                NotificationChannel(
                    it.channelId,
                    it.channelName,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Notifications about the general app"
                    group = it.groupId
                }
            }

        val notificationManager = appContext.getSystemService(
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.createNotificationChannelGroups(groups)
        notificationManager.createNotificationChannels(channels)
    }
}