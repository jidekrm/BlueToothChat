package com.ccrcgame.bluetoothchat.data.chat

import com.ccrcgame.bluetoothchat.domain.chat.BlueToothMessage

fun String.toBlueToothMessage(isFromLocalUser: Boolean): BlueToothMessage {
    val name = substringBeforeLast("#")
    val message = substringAfter("#")
    return BlueToothMessage(message, name, isFromLocalUser)
}


fun BlueToothMessage.toByteArray(): ByteArray {
    return "$senderName#$message".encodeToByteArray()
}