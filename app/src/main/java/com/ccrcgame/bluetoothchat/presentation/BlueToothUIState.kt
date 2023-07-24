package com.ccrcgame.bluetoothchat.presentation

import com.ccrcgame.bluetoothchat.domain.chat.BlueToothDeviceDomain

data class BlueToothUIState (
    val scannedDevices: List<BlueToothDeviceDomain> = emptyList(),
    val pairedDevices: List<BlueToothDeviceDomain> = emptyList()
        )