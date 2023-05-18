package com.matabel.myapplication.ui

import androidx.compose.runtime.Composable
import com.matabel.myapplication.navigation.LeftBehindNavHost

@Composable
fun LeftBehindApp(
    appState: LeftBehindAppState = rememberLeftBehindAppState(),
) {
    LeftBehindNavHost(appState)
}