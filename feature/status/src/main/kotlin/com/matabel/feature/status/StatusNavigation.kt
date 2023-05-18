package com.matabel.feature.status

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val statusRoute = "status_route"

fun NavController.navigateToStatus(navOptions: NavOptions? = null) {
    this.navigate(statusRoute, navOptions)
}

fun NavGraphBuilder.statusScreen(
    onSettingsClick: (address: String) -> Unit,
    onBtnClick: (address: String) -> Unit,
) {
    composable(route = statusRoute) {
        StatusRoute(
            onSettingsClick = onSettingsClick,
            onBtnClick = onBtnClick,
        )
    }
}
