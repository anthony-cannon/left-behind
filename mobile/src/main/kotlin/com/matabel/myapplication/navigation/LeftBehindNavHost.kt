package com.matabel.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.matabel.feature.status.statusRoute
import com.matabel.feature.status.statusScreen
import com.matabel.myapplication.ui.LeftBehindAppState

@Composable
fun LeftBehindNavHost(
    appState: LeftBehindAppState,
    modifier: Modifier = Modifier,
    startDestination: String = statusRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        statusScreen(
            onSettingsClick = {

            },
            onBtnClick = {

            },
        )
    }
}