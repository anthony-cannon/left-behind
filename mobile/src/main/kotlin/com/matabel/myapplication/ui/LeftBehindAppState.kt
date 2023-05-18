package com.matabel.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class LeftBehindAppState(
    val navController: NavHostController,
)

@Composable
fun rememberLeftBehindAppState(
    navController: NavHostController = rememberNavController(),
): LeftBehindAppState {
    return remember(
        navController,
    ) {
        LeftBehindAppState(
            navController,
        )
    }
}