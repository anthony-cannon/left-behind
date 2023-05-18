package com.matabel.core.notifications.di

import com.matabel.core.notifications.NotificationBuilder
import com.matabel.core.notifications.NotificationBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NotificationModule {
    @Binds
    fun bindsNotificationBuilder(
        notificationBuilder: NotificationBuilderImpl,
    ): NotificationBuilder
}