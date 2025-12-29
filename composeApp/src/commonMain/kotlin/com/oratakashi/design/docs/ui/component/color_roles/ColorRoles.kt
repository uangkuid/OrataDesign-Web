package com.oratakashi.design.docs.ui.component.color_roles

import androidx.compose.runtime.Composable
import com.oratakashi.design.docs.ui.component.color_roles.content.PrimaryColorRoleContent
import com.oratakashi.design.docs.ui.component.color_roles.content.SecondaryColorRoleContent
import com.oratakashi.design.docs.ui.component.color_roles.content.TertiaryColorRoleContent
import com.oratakashi.design.docs.ui.component.color_roles.content.ErrorColorRoleContent
import com.oratakashi.design.docs.ui.component.color_roles.content.SurfaceColorRoleContent
import com.oratakashi.design.docs.ui.component.color_roles.content.OutlineColorRoleContent
import com.oratakashi.design.docs.ui.component.color_roles.content.SurfaceDimColorRoleContent

enum class ColorRoles(
    val content: @Composable () -> Unit,
    val code: String
) {
    Primary(
        content = { PrimaryColorRoleContent() },
        code = "files/color_roles/PrimaryColorRoleContent.kt"
    ),
    Secondary(
        content = { SecondaryColorRoleContent() },
        code = "files/color_roles/SecondaryColorRoleContent.kt"
    ),
    Tertiary(
        content = { TertiaryColorRoleContent() },
        code = "files/color_roles/TertiaryColorRoleContent.kt"
    ),
    Error(
        content = { ErrorColorRoleContent() },
        code = "files/color_roles/ErrorColorRoleContent.kt"
    ),
    Surface(
        content = { SurfaceColorRoleContent() },
        code = "files/color_roles/SurfaceColorRoleContent.kt"
    ),
    Outline(
        content = { OutlineColorRoleContent() },
        code = "files/color_roles/OutlineColorRoleContent.kt"
    ),
    SurfaceDim(
        content = { SurfaceDimColorRoleContent() },
        code = "files/color_roles/SurfaceDimColorRoleContent.kt"
    )
}