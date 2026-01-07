package com.oratakashi.design.docs.ui.templates.alert

data class AlertConfig(
    val title: String,
    val description: String,
    val isVisible: Boolean,
    val includeOnClose: Boolean,
    val includeAction: Boolean
)
