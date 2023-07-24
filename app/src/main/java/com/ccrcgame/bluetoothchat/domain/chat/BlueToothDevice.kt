package com.ccrcgame.bluetoothchat.domain.chat
typealias BlueToothDeviceDomain = com.ccrcgame.bluetoothchat.domain.chat.BlueToothDevice

data class BlueToothDevice(
    val name: String?,
    val address: String,
) {
}