package com.ccrcgame.bluetoothchat.data.chat

import android.bluetooth.BluetoothSocket
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothMessage
import com.ccrcgame.bluetoothchat.domain.chat.TransferFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class BlueToothDataTransferService(
    private val socket: BluetoothSocket
) {
    fun listenForIncomingMessages(): Flow<BlueToothMessage> {
        return flow {
            if (!socket.isConnected) {
                return@flow
            }
            val buffer = ByteArray(1024)
            while (true) {
                val byteCount = try {
                    socket.inputStream.read(buffer)
                } catch (e: IOException) {
                    throw TransferFailedException()
                }

                emit(
                    buffer.decodeToString(endIndex = byteCount)
                        .toBlueToothMessage(isFromLocalUser = false)
                )


            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun sendMessage(bytes: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                socket.outputStream.write(bytes)
            }catch (e: IOException){
                e.printStackTrace()
                return@withContext false
            }

            true
        }
    }

}