package com.oratakashi.design.docs.ui.templates.anchortext

import androidx.compose.runtime.Composable
import com.oratakashi.design.component.anchortext.OraAnchorText

@Composable
fun AnchorText(
    config: AnchorTextConfig
) {
    OraAnchorText(
        text = config.text,
        onClick = {
            //TODO: Add Action Here
        }
    )
}