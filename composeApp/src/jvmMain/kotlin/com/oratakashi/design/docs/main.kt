package com.oratakashi.design.docs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.oratakashi.design.docs.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Orata Design",
    ) {
        App()
    }
}