package com.oratakashi.design.docs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.oratakashi.design.docs.ui.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App()
    }
}