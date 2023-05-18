package com.matabel.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matabel.core.designsystem.LeftBehindTheme
import com.matabel.myapplication.permission.Permission
import com.matabel.myapplication.permission.PermissionManager
import com.matabel.myapplication.permission.PermissionRequester
import com.matabel.myapplication.ui.LeftBehindApp
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val permissionRequester = PermissionRequester(this)

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            val darkTheme = isSystemInDarkTheme()

            // Update the dark content of the system bars to match the theme
            DisposableEffect(systemUiController, darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !darkTheme
                onDispose {}
            }

            LeftBehindTheme(darkTheme) {
                LeftBehindApp()
                val permissionManager =
                    PermissionManager.from(this@MainActivity, permissionRequester)
                permissionManager
                    .request(
                        Permission.Bluetooth,
                        Permission.BluetoothConnect,
                        Permission.BluetoothScan,
                        Permission.BluetoothAdmin,
                        Permission.NotificationsPost,
                        Permission.LocationFine,
                    )
                    .displayRationale {
                        Timber.e("displayRationale")
                    }
                    .checkDetailedPermission {
                        Timber.e("checkDetailedPermission")
                    }
            }
        }
    }
}