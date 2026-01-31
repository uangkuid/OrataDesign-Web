package com.oratakashi.design.docs.ui.templates.button

import com.oratakashi.design.component.button.OraButtonSize

data class ButtonConfig(
    val label: String,
    val isEnabled: Boolean,
    val showIconLeft: Boolean,
    val showRightIcon: Boolean,
    val size: OraButtonSize
)
