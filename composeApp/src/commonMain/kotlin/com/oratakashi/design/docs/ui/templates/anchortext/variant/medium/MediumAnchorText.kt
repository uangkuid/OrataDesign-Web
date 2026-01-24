package com.oratakashi.design.docs.ui.templates.anchortext.variant.medium

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.anchortext.OraAnchorText
import com.oratakashi.design.component.anchortext.OraAnchorTextSize
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.ArrowRight

@Composable
fun MediumAnchorText() {
    OraAnchorText(
        text = "Label",
        size = OraAnchorTextSize.Medium,
        onClick = {

        },
        iconLeft = {
            Icon(
                imageVector = FeatherIcons.ArrowLeft,
                contentDescription = null
            )
        },
        iconRight = {
            Icon(
                imageVector = FeatherIcons.ArrowRight,
                contentDescription = null
            )
        }
    )
}