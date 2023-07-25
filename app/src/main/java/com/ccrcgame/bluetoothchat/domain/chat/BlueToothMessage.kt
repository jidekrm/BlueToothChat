package com.ccrcgame.bluetoothchat.domain.chat

data class BlueToothMessage(
    val message: String,
    val senderName: String,
    val isFromLocalUser: Boolean
)
