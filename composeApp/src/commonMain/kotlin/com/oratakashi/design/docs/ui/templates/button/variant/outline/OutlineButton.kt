package com.oratakashi.design.docs.ui.templates.button.variant.outline

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.button.OraButton
import com.oratakashi.design.component.button.OraOutlineButton
import com.oratakashi.design.component.button.OraTonalButton
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.ArrowRight

@Composable
fun OutlineButton() {
    OraOutlineButton(
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