package com.ccrcgame.bluetoothchat.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBlueToothDeviceDomain(): BlueToothDeviceDomain {
    return BlueToothDeviceDomain(
        name = name,
        address = address
    )
}