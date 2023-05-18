package com.matabel.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matabel.core.designsystem.LeftBehindTheme

@Composable
fun SettingsRoute(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsDialog(
        onDismiss = onDismiss,
        settingsUiState = settingsUiState,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsDialog(
    settingsUiState: SettingsUiState,
    onDismiss: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    AlertDialog(
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Divider()
            Column(Modifier.verticalScroll(rememberScrollState())) {
                when (settingsUiState) {
                    is SettingsUiState.Loading -> {
                        Text(
                            text = "Loading...",
                            modifier = Modifier.padding(vertical = 16.dp),
                        )
                    }

                    is SettingsUiState.Success -> {
                        SettingsPanel(
//                            settings = settingsUiState.settings,
//                            supportDynamicColor = supportDynamicColor,
//                            onChangeThemeBrand = onChangeThemeBrand,
//                            onChangeDynamicColorPreference = onChangeDynamicColorPreference,
//                            onChangeDarkThemeConfig = onChangeDarkThemeConfig,
                        )
                    }
                }
                Divider(Modifier.padding(top = 8.dp))
            }
//            TrackScreenViewEvent(screenName = "Settings")
        },
        confirmButton = {
            Text(
                text = "OK",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() },
            )
        },
    )
}

@Composable
private fun SettingsPanel(
//    settings: UserEditableSettings,
//    supportDynamicColor: Boolean,
//    onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
//    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
//    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
//    SettingsDialogSectionTitle(text = stringResource(string.theme))
//    Column(Modifier.selectableGroup()) {
//        SettingsDialogThemeChooserRow(
//            text = stringResource(string.brand_default),
//            selected = settings.brand == DEFAULT,
//            onClick = { onChangeThemeBrand(DEFAULT) },
//        )
//        SettingsDialogThemeChooserRow(
//            text = stringResource(string.brand_android),
//            selected = settings.brand == ANDROID,
//            onClick = { onChangeThemeBrand(ANDROID) },
//        )
//    }
//    if (settings.brand == DEFAULT && supportDynamicColor) {
//        SettingsDialogSectionTitle(text = stringResource(R.string.dynamic_color_preference))
//        Column(Modifier.selectableGroup()) {
//            SettingsDialogThemeChooserRow(
//                text = stringResource(string.dynamic_color_yes),
//                selected = settings.useDynamicColor,
//                onClick = { onChangeDynamicColorPreference(true) },
//            )
//            SettingsDialogThemeChooserRow(
//                text = stringResource(string.dynamic_color_no),
//                selected = !settings.useDynamicColor,
//                onClick = { onChangeDynamicColorPreference(false) },
//            )
//        }
//    }
//    SettingsDialogSectionTitle(text = stringResource(R.string.dark_mode_preference))
//    Column(Modifier.selectableGroup()) {
//        SettingsDialogThemeChooserRow(
//            text = stringResource(string.dark_mode_config_system_default),
//            selected = settings.darkThemeConfig == FOLLOW_SYSTEM,
//            onClick = { onChangeDarkThemeConfig(FOLLOW_SYSTEM) },
//        )
//        SettingsDialogThemeChooserRow(
//            text = stringResource(string.dark_mode_config_light),
//            selected = settings.darkThemeConfig == LIGHT,
//            onClick = { onChangeDarkThemeConfig(LIGHT) },
//        )
//        SettingsDialogThemeChooserRow(
//            text = stringResource(string.dark_mode_config_dark),
//            selected = settings.darkThemeConfig == DARK,
//            onClick = { onChangeDarkThemeConfig(DARK) },
//        )
//    }
}

//@Composable
//private fun SettingsDialogSectionTitle(text: String) {
//    Text(
//        text = text,
//        style = MaterialTheme.typography.titleMedium,
//        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
//    )
//}
//
//@Composable
//fun SettingsDialogThemeChooserRow(
//    text: String,
//    selected: Boolean,
//    onClick: () -> Unit,
//) {
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .selectable(
//                selected = selected,
//                role = Role.RadioButton,
//                onClick = onClick,
//            )
//            .padding(12.dp),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        RadioButton(
//            selected = selected,
//            onClick = null,
//        )
//        Spacer(Modifier.width(8.dp))
//        Text(text)
//    }
//}

@Preview
@Composable
private fun PreviewSettingsDialog() {
    LeftBehindTheme {
        SettingsDialog(
            onDismiss = {},
            settingsUiState = SettingsUiState.Success,
        )
    }
}

@Preview
@Composable
private fun PreviewSettingsLoadingDialog() {
    LeftBehindTheme {
        SettingsDialog(
            onDismiss = {},
            settingsUiState = SettingsUiState.Loading,
        )
    }
}