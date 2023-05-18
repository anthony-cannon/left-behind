package com.matabel.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matabel.core.designsystem.LeftBehindTheme
import com.matabel.core.model.BtDevice
import com.matabel.core.model.BtDeviceSettings

@Composable
fun DeviceCard(
    btDevice: BtDevice,
    modifier: Modifier = Modifier,
    onSettingsClick: (btAddress: String) -> Unit,
    onBtnClick: (btAddress: String) -> Unit,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Device Settings"
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onSettingsClick(btDevice.address) }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Device Settings"
                    )
                }
            }
            DeviceCardTitle(
                title = "Headline",
                modifier = Modifier.fillMaxWidth((.8f)),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (btDevice.settings.canObserve) "Observing" else "Ignoring"
                )
                Spacer(modifier = Modifier.size(6.dp))
                ConnectionDot(
                    if (btDevice.settings.canObserve) Color.Green else Color.Red,
                    modifier = Modifier.size(8.dp),
                )
            }
        }
    }
}

@Composable
fun DeviceCardTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}

@Composable
fun ConnectionDot(
    color: Color,
    modifier: Modifier = Modifier,
) {
//    val description = stringResource(R.string.unread_resource_dot_content_description)
    Canvas(
        modifier = modifier
            .semantics { contentDescription = "description" },
        onDraw = {
            drawCircle(
                color,
                radius = size.minDimension / 2,
            )
        },
    )
}

@Preview("Device Card Connected Preview")
@Composable
fun DeviceCardConnectedPreview() {
    LeftBehindTheme {
        DeviceCard(BtDevice("", "", null, BtDeviceSettings(true)), Modifier, {}) {}
    }
}

@Preview("Device Card Preview")
@Composable
fun DeviceCardPreview() {
    LeftBehindTheme {
        DeviceCard(BtDevice("", "", null, BtDeviceSettings(false)), Modifier, {}) {}
    }
}