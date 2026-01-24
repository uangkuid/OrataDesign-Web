package com.oratakashi.design.docs.ui.templates.anchortext

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.oratakashi.design.component.anchortext.OraAnchorText
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.ArrowRight

@Composable
fun AnchorText(
    config: AnchorTextConfig
) {
    OraAnchorText(
        enabled = config.isEnable,
        text = config.text,
        underline = config.showUnderline,
        iconLeft = if (config.showIconLeft) {
            {
                Icon(imageVector = FeatherIcons.ArrowLeft, contentDescription = null)
            }
        } else null,
        iconRight = if (config.showRightIcon) {
            {
                Icon(imageVector = FeatherIcons.ArrowRight, contentDescription = null)
            }
        } else null,
        onClick = {
            //TODO: Add Action Here
        }
    )
}