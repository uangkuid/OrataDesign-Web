package com.oratakashi.design.docs.helpers

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue

/**
 * NavigationHelpers for Navigation purpose
 * @author oratakashi
 * @since 16 Dec 2025
 */
object NavigationHelpers {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun isListDetailPaneOpened(listPane: ThreePaneScaffoldValue): Boolean {
        return listPane.primary.toString() == "PaneAdaptedValue[Expanded]" && listPane.secondary.toString() == "PaneAdaptedValue[Expanded]"
    }
}