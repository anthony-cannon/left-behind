package com.matabel.myapplication.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

class PermissionManager private constructor(
    private val activity: WeakReference<ComponentActivity>,
    private val permissionRequester: PermissionRequester,
) {

    private val requiredPermissions = mutableListOf<Permission>()
    private var detailedCallback: (List<PermissionResult>) -> Unit = {}
    private var displayRationale: (Permission) -> Unit = {}

    companion object {
        fun from(
            activity: ComponentActivity,
            permissionRequester: PermissionRequester,
        ) = PermissionManager(WeakReference(activity), permissionRequester)
    }

    fun request(vararg permission: Permission): PermissionManager {
        requiredPermissions.addAll(permission)
        return this
    }

    fun displayRationale(
        displayRationale: (permission: Permission) -> Unit
    ): PermissionManager {
        this.displayRationale = displayRationale
        return this
    }

    fun checkDetailedPermission(callback: (results: List<PermissionResult>) -> Unit) {
        this.detailedCallback = callback
        handlePermissionRequest()
    }

    private fun handlePermissionRequest() {
        activity.get()?.let { activity ->
            val results = requiredPermissions
                .map { permission ->
                    PermissionResult(permission, permission.isGranted(activity))
                }
                .toMutableList()
            results
                .filter { !it.granted }
                .ifNotEmpty {
                    // TODO rationale doesn't work
                    forEach { result ->
                        if (result.permission.requiresRationale(activity)) {
                            displayRationale(result.permission)
                        }
                    }
                    permissionRequester.requestMultiplePermissions(
                        map { it.permission.permission }.toTypedArray()
                    ) { requestResult ->
                        requestResult.forEach { p1r ->
                            val i = results.indexOfFirst { it.permission.permission == p1r.key }
                            results[i] = results[i].copy(granted = p1r.value)
                        }
                        sendResultAndCleanUp(results)
                    }
                    this
                }
                .ifEmpty {
                    sendResultAndCleanUp(results)
                }
        }
    }

    private fun sendResultAndCleanUp(grantResults: List<PermissionResult>) {
        detailedCallback(grantResults)
        cleanUp()
    }

    private fun cleanUp() {
        requiredPermissions.clear()
        detailedCallback = {}
        displayRationale = {}
    }

    private fun Permission.requiresRationale(activity: Activity) =
        activity.shouldShowRequestPermissionRationale(permission)

    private fun Permission.isGranted(context: Context) =
        hasPermission(context, permission)

    private fun hasPermission(context: Context, permission: String) =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
}

inline fun <C : Collection<*>> C.ifNotEmpty(block: C.() -> C): C =
    if (isNotEmpty()) block(this) else this
