package me.y9san9.ddxofflineqr

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "ddx-offline-qr") {
        val colors = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
        MaterialTheme(colors) {
            App()
        }
    }
}
