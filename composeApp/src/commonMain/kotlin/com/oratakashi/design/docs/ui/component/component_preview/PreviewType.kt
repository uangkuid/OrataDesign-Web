package com.oratakashi.design.docs.ui.component.component_preview

/**
 * PreviewType is a sealed class that represents the type of preview available for a component in the documentation UI.
 *
 * Use this to distinguish between the default preview and specific variant previews.
 *
 * @author oratakashi
 * @since 18 Jan 2026
 */
sealed class PreviewType {
    /**
     * Default is an object representing the standard preview type for a component.
     */
    object Default: PreviewType()

    /**
     * Variant is a data class representing a specific variant of a component preview.
     * @param name The name of the variant to be displayed in the preview.
     */
    data class Variant(val name: String): PreviewType()
}