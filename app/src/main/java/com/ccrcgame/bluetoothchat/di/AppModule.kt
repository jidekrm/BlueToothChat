package com.ccrcgame.bluetoothchat.di

import android.content.Context
import com.ccrcgame.bluetoothchat.data.chat.AndroidBlueToothController
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBlueToothController(@ApplicationContext context: Context): BlueToothController {
        return AndroidBlueToothController(context)
    }
}