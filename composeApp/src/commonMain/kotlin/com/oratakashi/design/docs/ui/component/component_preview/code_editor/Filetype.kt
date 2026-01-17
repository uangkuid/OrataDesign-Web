package com.oratakashi.design.docs.ui.component.component_preview.code_editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FeatherIcons
import compose.icons.feathericons.File
import oratadesign_web.composeapp.generated.resources.Res
import oratadesign_web.composeapp.generated.resources.ic_kotlin
import org.jetbrains.compose.resources.vectorResource

sealed class Filetype {
    @Composable
    abstract fun icons(): ImageVector

    object Kotlin: Filetype() {
        @Composable
        override fun icons(): ImageVector {
            return vectorResource(Res.drawable.ic_kotlin)
        }
    }

    object Unknown: Filetype() {
        @Composable
        override fun icons(): ImageVector {
            return FeatherIcons.File
        }
    }

    companion object {
        fun from(name: String): Filetype {
            return when {
                name.endsWith(".kt") -> Kotlin
                name == Kotlin::class.simpleName -> Kotlin
                else -> Unknown
            }
        }
    }
}