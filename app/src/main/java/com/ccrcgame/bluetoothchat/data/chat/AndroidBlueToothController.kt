package com.ccrcgame.bluetoothchat.data.chat

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothController
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothDevice
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothDeviceDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("MissingPermission")
class AndroidBlueToothController(
    private val context: Context
) : BlueToothController {
    private val blueToothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        blueToothManager?.adapter
    }

    private val _scannedDevices: MutableStateFlow<List<BlueToothDeviceDomain>> =
        MutableStateFlow(emptyList())
    override val scannedDevices: StateFlow<List<BlueToothDevice>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices: MutableStateFlow<List<BlueToothDeviceDomain>> =
        MutableStateFlow(emptyList())
    override val pairedDevices: StateFlow<List<BlueToothDevice>>
        get() = _pairedDevices.asStateFlow()

private val foundDeviceReceiver = FoundDeviceReceiver {device->
    _scannedDevices.update{devices->
        val newDevice = device.toBlueToothDeviceDomain()
        if (newDevice in devices) devices else devices + newDevice
    }

}
    init {
        updatePairedDevices()
    }

    override fun startDiscovery() {
       if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)){
           return
       }

        context.registerReceiver(
            foundDeviceReceiver,
            IntentFilter(BluetoothDevice.ACTION_FOUND)
        )
        updatePairedDevices()
        bluetoothAdapter?.startDiscovery()
    }

    override fun stopDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)){
            return
        }

        bluetoothAdapter?.cancelDiscovery()
    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
    }


    private fun updatePairedDevices() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return
        }
        bluetoothAdapter?.bondedDevices?.map { it.toBlueToothDeviceDomain() }
            ?.also { devices -> _pairedDevices.update { devices } }
    }


    private fun hasPermission(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

}