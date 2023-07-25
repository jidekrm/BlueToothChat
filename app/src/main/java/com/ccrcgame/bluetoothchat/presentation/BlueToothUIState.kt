package com.ccrcgame.bluetoothchat.presentation

import com.ccrcgame.bluetoothchat.domain.chat.BlueToothDeviceDomain
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothMessage

data class BlueToothUIState(
    val scannedDevices: List<BlueToothDeviceDomain> = emptyList(),
    val pairedDevices: List<BlueToothDeviceDomain> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
    val messages: List<BlueToothMessage> = emptyList()
)