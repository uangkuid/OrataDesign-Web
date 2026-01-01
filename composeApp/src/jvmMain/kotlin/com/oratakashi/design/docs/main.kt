package com.oratakashi.design.docs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.oratakashi.design.docs.di.AppModule
import com.oratakashi.design.docs.ui.App
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun main() = application {
    startKoin {
        modules(*AppModule.provideModule())
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "Orata Design",
    ) {
        App()
    }
}