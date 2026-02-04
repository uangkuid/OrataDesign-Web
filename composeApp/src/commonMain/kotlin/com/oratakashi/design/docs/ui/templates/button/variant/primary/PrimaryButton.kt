package com.oratakashi.design.docs.ui.templates.button.variant.primary

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.button.OraButton
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.ArrowRight

@Composable
fun PrimaryButton() {
    OraButton(
        onClick = {

        },
        label = "Button",
        iconLeft = {
            Icon(imageVector = FeatherIcons.ArrowLeft, contentDescription = null)
        },
        iconRight = {
            Icon(imageVector = FeatherIcons.ArrowRight, contentDescription = null)
        }
    )
}