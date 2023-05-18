package com.matabel.myapplication.ui.devices
//
//import androidx.activity.ComponentActivity
//import com.matabel.myapplication.data.DeviceEnt
//import com.matabel.myapplication.ui.device.settings.DeviceSettingsScreen
//import kotlinx.coroutines.launch
//
//class DevicesActions(
//    activity: ComponentActivity,
//    permissions: DevicesBluetoothUseCase,
//    private val navigateTo: (String) -> Unit = {},
//    private val viewModel: DevicesViewModel,
//    private val preferences: LBPreferences,
//) : ScreenActions() {
//
//    init {
//        permissions.getBluetoothBondedDevices(activity) { bondedDevices ->
//            screenActionsScope.launch {
//                val connectedDevices = preferences.getDevices()
//
//                val deviceList = bondedDevices.map { db ->
//                    db.copy(isConnected = connectedDevices.any { it.address == db.address })
//                }
//
//                viewModel.setBluetoothList(deviceList)
//            }
//        }
//    }
//
//    fun navigateBack() {
//        navigateTo(NavBack.route)
//    }
//
//    fun connectToDevice(deviceEntity: DeviceEnt) {
//
//    }
//
//    fun navigateToDeviceSettings(deviceEntity: DeviceEnt) {
//        navigateTo(DeviceSettingsScreen.route(deviceEntity.address))
//    }
//}