package com.ccrcgame.bluetoothchat.domain.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BlueToothController {

    val isConnected: StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BlueToothDevice>>
    val pairedDevices: StateFlow<List<BlueToothDevice>>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()


    fun startBlueToothServer(): Flow<ConnectionResult>
    fun connectToDevice(device: BlueToothDevice): Flow<ConnectionResult>

    suspend fun trySendMessage(message: String): BlueToothMessage?
    fun closeConnection()
    fun release()
}