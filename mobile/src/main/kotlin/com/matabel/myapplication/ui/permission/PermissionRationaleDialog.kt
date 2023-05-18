package com.matabel.myapplication.ui.permission

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

object PermissionRationaleDialog {

    @Composable
    operator fun invoke(
        permission: String,
        rationale: String,
    ) {
        AlertDialog(
            title = {
                Text("LeftBehind needs access to your $permission")
            },
            text = {
                Text(rationale)
            },
            confirmButton = {
                TextButton(
                    onClick = {

                    },
                    content = {
                        Text("Ok")
                    }
                )
            },
            onDismissRequest = {

            }
        )
    }
}

@Preview
@Composable
fun PermissionRationaleDialogPreview() {
    PermissionRationaleDialog(
        "PermissionX",
        "X is very important to us",
    )
}
