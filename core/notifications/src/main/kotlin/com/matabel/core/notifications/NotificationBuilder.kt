package com.matabel.core.notifications

import android.content.Context
import com.matabel.core.notifications.model.LBNotificationType

interface NotificationBuilder {
    fun show(context: Context, type: LBNotificationType)
    fun cancel(context: Context, type: LBNotificationType)
}