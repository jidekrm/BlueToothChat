package com.ccrcgame.bluetoothchat.presentation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccrcgame.bluetoothchat.presentation.components.DeviceScreen
import com.ccrcgame.bluetoothchat.ui.theme.BlueToothChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val blueToothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        blueToothManager?.adapter
    }

    private val isBlueToothEnabled : Boolean
        get() = bluetoothAdapter?.isEnabled == false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val enableBlueToothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            /* nOT nEEDED */
        }
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){perms ->
            val canEnableBlueTooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[android.Manifest.permission.BLUETOOTH_CONNECT] ?: true
            } else true

            if (canEnableBlueTooth && !isBlueToothEnabled) {
                enableBlueToothLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
            }
        }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                permissionLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.BLUETOOTH_SCAN,
                        android.Manifest.permission.BLUETOOTH_CONNECT,
                    )
                )
            }


        setContent {
            BlueToothChatTheme {

                val viewModel = hiltViewModel<BlueToothViewModel>()
                val state by viewModel.state.collectAsState()






                Surface( color = MaterialTheme.colorScheme.background) {
                   DeviceScreen(
                       state = state,
                       onStartScan = viewModel::startScan,
                       onStopScan = viewModel::stopScan
                   )
                }
            }
        }
    }
}

