package com.ccrcgame.bluetoothchat.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ccrcgame.bluetoothchat.domain.chat.BlueToothDevice
import com.ccrcgame.bluetoothchat.presentation.BlueToothUIState

@Composable
fun DeviceScreen(
    state: BlueToothUIState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClick: (BlueToothDevice) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BlueToothDeviceList(
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices,
            onClick = onDeviceClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = onStartScan) {
                Text(text = "Start Scan")
            }
            Button(onClick = onStopScan) {
                Text(text = "Stop Scan")
            }

            Button(onClick = onStartServer) {
                Text(text = "Start Server")
            }

        }
    }

}


@Composable
fun BlueToothDeviceList(
    pairedDevices: List<BlueToothDevice>,
    scannedDevices: List<BlueToothDevice>,
    onClick: (BlueToothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(pairedDevices) { device ->
            Text(text = device.name ?: "(No Name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }

        item {
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(scannedDevices) { device ->
            Text(text = device.name ?: "(No Name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }
    }
}