package com.ccrcgame.bluetoothchat.domain.chat

sealed interface ConnectionResult {
    object ConnectionEstablished : ConnectionResult
    data class TransferSucceeded(val message: BlueToothMessage) : ConnectionResult
    data class Error(val message: String) : ConnectionResult
}