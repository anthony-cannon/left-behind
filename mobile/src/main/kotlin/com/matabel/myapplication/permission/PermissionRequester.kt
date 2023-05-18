package com.matabel.myapplication.permission

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class PermissionRequester(activity: ComponentActivity) {

    private var callback: (Map<String, @JvmSuppressWildcards Boolean>) -> Unit = {}

    private val permissionCheck =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grantResults ->
            if (grantResults.isNotEmpty()) {
                callback(grantResults)
                callback = {}
            }
        }

    fun requestMultiplePermissions(
        permissions: Array<String>,
        callback: (Map<String, @JvmSuppressWildcards Boolean>) -> Unit,
    ) {
        this.callback = callback
        permissionCheck.launch(permissions)
    }
}