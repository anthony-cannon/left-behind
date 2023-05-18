package com.matabel.feature.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matabel.core.model.BtDevice
import com.matabel.core.model.BtDeviceSettings
import com.matabel.core.ui.DeviceCard

@Composable
fun StatusRoute(
    viewModel: StatusViewModel = hiltViewModel(),
    onSettingsClick: (address: String) -> Unit,
    onBtnClick: (address: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StatusScreen(
        uiState,
        onSettingsClick = onSettingsClick,
        onBtnClick = onBtnClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusScreen(
    uiState: StatusUiState,
    onSettingsClick: (address: String) -> Unit,
    onBtnClick: (address: String) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Left Behind",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp),
                columns = GridCells.Adaptive(minSize = 180.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiState.btDevices, key = { it.address }) { btDevice ->
                    DeviceCard(
                        btDevice = btDevice,
                        onSettingsClick = onSettingsClick,
                        onBtnClick = onBtnClick
                    )
                }
            }
        }
    )
}

@Preview("Status Screen Preview")
@Composable
fun StatusScreenPreview() {
    StatusScreen(
        StatusUiState(
            listOf(
                BtDevice(
                    "",
                    "",
                    null,
                    BtDeviceSettings(false)
                ),
                BtDevice(
                    "",
                    "",
                    null,
                    BtDeviceSettings(false)
                ),
                BtDevice(
                    "",
                    "",
                    null,
                    BtDeviceSettings(false)
                ),
                BtDevice(
                    "",
                    "",
                    null,
                    BtDeviceSettings(false)
                )
            )
        ),
        {}
    ) {}
}