package com.matabel.core.notifications.old
//
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationChannelGroup
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import androidx.core.app.NotificationCompat
//import androidx.core.content.ContextCompat.getSystemService
//import com.matabel.myapplication.MainActivity
//
//class TestNotification {
//    companion object {
//        private const val REQUEST_CODE = 0
//    }
//
//    fun create(appContext: Context, title: String, content: String): Notification {
//
//        val fullScreenIntent = Intent(appContext, MainActivity::class.java)
//        val fullScreenPendingIntent = PendingIntent.getActivity(
//            appContext,
//            REQUEST_CODE,
//            fullScreenIntent,
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                PendingIntent.FLAG_MUTABLE
//            } else {
//                PendingIntent.FLAG_ONE_SHOT
//            },
//        )
//
//        val notificationBuilder =
//            NotificationCompat.Builder(appContext, "channel_phone_notify")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle(title)
//                .setContentText(content)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_EVENT)
//
//                // Use a full-screen intent only for the highest-priority alerts where you
//                // have an associated activity that you would like to launch after the user
//                // interacts with the notification. Also, if your app targets Android 10
//                // or higher, you need to request the USE_FULL_SCREEN_INTENT permission in
//                // order for the platform to invoke this notification.
//                .setFullScreenIntent(fullScreenPendingIntent, true)
//
//        return notificationBuilder.build()
//    }
//
//}