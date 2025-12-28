package com.oratakashi.design.docs.ui.component.colorschema

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.oratakashi.design.foundation.OrataAppTheme
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun ColorSchemaPreview(
    isDark: Boolean = true
) {
    OrataAppTheme(
        darkTheme = isDark
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = OrataTheme.colors.surface,
                contentColor = OrataTheme.colors.onSurface
            ),
            border = BorderStroke(
                width = 2.dp,
                color = OrataTheme.colors.outline
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (rowNormal, rowAccent, rowSurface, footer) = createRefs()

                Text(
                    text = "Diagram of all Orata Design color roles, including optional add-on roles for surface colors and fixed accent colors",
                    modifier = Modifier.constrainAs(footer) {
                        bottom.linkTo(parent.bottom, 16.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)

                        width = Dimension.fillToConstraints
                    }
                )

                FlowRow(
                    modifier = Modifier.constrainAs(rowNormal) {
                        top.linkTo(parent.top, 24.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                        bottom.linkTo(rowAccent.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }

                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                            .fillMaxHeight()
                    ) {
                        ColorSchemaItem(
                            text = "Primary",
                            backgroundColor = OrataTheme.colors.primary,
                            contentColor = OrataTheme.colors.onPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Primary",
                            backgroundColor = OrataTheme.colors.onPrimary,
                            contentColor = OrataTheme.colors.primary,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Primary Container",
                            backgroundColor = OrataTheme.colors.primaryContainer,
                            contentColor = OrataTheme.colors.onPrimaryContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Primary Container",
                            backgroundColor = OrataTheme.colors.onPrimaryContainer,
                            contentColor = OrataTheme.colors.primaryContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        ColorSchemaItem(
                            text = "Secondary",
                            backgroundColor = OrataTheme.colors.secondary,
                            contentColor = OrataTheme.colors.onSecondary,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Secondary",
                            backgroundColor = OrataTheme.colors.onSecondary,
                            contentColor = OrataTheme.colors.secondary,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Secondary Container",
                            backgroundColor = OrataTheme.colors.secondaryContainer,
                            contentColor = OrataTheme.colors.onSecondaryContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Secondary Container",
                            backgroundColor = OrataTheme.colors.onSecondaryContainer,
                            contentColor = OrataTheme.colors.secondaryContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        ColorSchemaItem(
                            text = "Tertiary",
                            backgroundColor = OrataTheme.colors.tertiary,
                            contentColor = OrataTheme.colors.onTertiary,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Tertiary",
                            backgroundColor = OrataTheme.colors.onTertiary,
                            contentColor = OrataTheme.colors.tertiary,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Tertiary Container",
                            backgroundColor = OrataTheme.colors.tertiaryContainer,
                            contentColor = OrataTheme.colors.onTertiaryContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Tertiary Container",
                            backgroundColor = OrataTheme.colors.onTertiaryContainer,
                            contentColor = OrataTheme.colors.tertiaryContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {
                        ColorSchemaItem(
                            text = "Error",
                            backgroundColor = OrataTheme.colors.error,
                            contentColor = OrataTheme.colors.onError,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Error",
                            backgroundColor = OrataTheme.colors.onError,
                            contentColor = OrataTheme.colors.error,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Error Container",
                            backgroundColor = OrataTheme.colors.errorContainer,
                            contentColor = OrataTheme.colors.onErrorContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Error Container",
                            backgroundColor = OrataTheme.colors.onErrorContainer,
                            contentColor = OrataTheme.colors.errorContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                FlowRow(
                    modifier = Modifier.constrainAs(rowAccent) {
                        top.linkTo(rowNormal.bottom, 24.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                        bottom.linkTo(rowSurface.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                            .fillMaxHeight()
                    ) {
                        ColorSchemaItem(
                            text = "Info",
                            backgroundColor = OrataTheme.colors.info,
                            contentColor = OrataTheme.colors.onInfo,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Info",
                            backgroundColor = OrataTheme.colors.onInfo,
                            contentColor = OrataTheme.colors.info,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Info Container",
                            backgroundColor = OrataTheme.colors.infoContainer,
                            contentColor = OrataTheme.colors.onInfoContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Info Container",
                            backgroundColor = OrataTheme.colors.onInfoContainer,
                            contentColor = OrataTheme.colors.infoContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        ColorSchemaItem(
                            text = "Warning",
                            backgroundColor = OrataTheme.colors.warning,
                            contentColor = OrataTheme.colors.onWarning,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Warning",
                            backgroundColor = OrataTheme.colors.onWarning,
                            contentColor = OrataTheme.colors.warning,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Warning Container",
                            backgroundColor = OrataTheme.colors.warningContainer,
                            contentColor = OrataTheme.colors.onWarningContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Warning Container",
                            backgroundColor = OrataTheme.colors.onWarningContainer,
                            contentColor = OrataTheme.colors.warningContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        ColorSchemaItem(
                            text = "Success",
                            backgroundColor = OrataTheme.colors.success,
                            contentColor = OrataTheme.colors.onSuccess,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "On Success",
                            backgroundColor = OrataTheme.colors.onSuccess,
                            contentColor = OrataTheme.colors.success,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Success Container",
                            backgroundColor = OrataTheme.colors.successContainer,
                            contentColor = OrataTheme.colors.onSuccessContainer,
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        )

                        ColorSchemaItem(
                            text = "On Success Container",
                            backgroundColor = OrataTheme.colors.onSuccessContainer,
                            contentColor = OrataTheme.colors.successContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Box (
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {
                    }
                }

                FlowRow(
                    modifier = Modifier.constrainAs(rowSurface) {
                        top.linkTo(rowAccent.bottom, 24.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                        bottom.linkTo(footer.top, 16.dp)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                ) {
                    Column(
                        modifier = Modifier.weight(3f)
                            .fillMaxHeight()
                    ) {
                        Row(
                            modifier = Modifier.weight(2f)
                        ) {
                            ColorSchemaItem(
                                text = "Surface Dim ",
                                backgroundColor = OrataTheme.colors.surfaceDim,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                            ColorSchemaItem(
                                text = "Surface",
                                backgroundColor = OrataTheme.colors.surface,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )

                            ColorSchemaItem(
                                text = "Surface Bright",
                                backgroundColor = OrataTheme.colors.surfaceBright,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Row(
                            modifier = Modifier.weight(2f)
                                .padding(top = 4.dp)
                        ) {
                            ColorSchemaItem(
                                text = "Surface Container Lowest",
                                backgroundColor = OrataTheme.colors.surfaceContainerLowest,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                            ColorSchemaItem(
                                text = "Surface Container Low",
                                backgroundColor = OrataTheme.colors.surfaceContainerLow,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )

                            ColorSchemaItem(
                                text = "Surface Container",
                                backgroundColor = OrataTheme.colors.surfaceContainer,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                            ColorSchemaItem(
                                text = "Surface Container High",
                                backgroundColor = OrataTheme.colors.surfaceContainerHigh,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                            ColorSchemaItem(
                                text = "Surface Container Highest",
                                backgroundColor = OrataTheme.colors.surfaceContainerHighest,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Row(
                            modifier = Modifier.weight(1f)
                                .padding(top = 4.dp)
                        ) {
                            ColorSchemaItem(
                                text = "On Surface",
                                backgroundColor = OrataTheme.colors.onSurface,
                                contentColor = OrataTheme.colors.surface,
                                modifier = Modifier.weight(1f)
                            )
                            ColorSchemaItem(
                                text = "On Surface Variant",
                                backgroundColor = OrataTheme.colors.onSurfaceVariant,
                                contentColor = OrataTheme.colors.surfaceVariant,
                                modifier = Modifier.weight(1f)
                            )

                            ColorSchemaItem(
                                text = "Outline",
                                backgroundColor = OrataTheme.colors.outline,
                                contentColor = OrataTheme.colors.surfaceContainerHighest,
                                modifier = Modifier.weight(1f)
                            )
                            ColorSchemaItem(
                                text = "Outline Variant",
                                backgroundColor = OrataTheme.colors.outlineVariant,
                                contentColor = OrataTheme.colors.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {
                        ColorSchemaItem(
                            text = "Inverse Surface",
                            backgroundColor = OrataTheme.colors.inverseSurface,
                            contentColor = OrataTheme.colors.inverseOnSurface,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "Inverse On Surface",
                            backgroundColor = OrataTheme.colors.inverseOnSurface,
                            contentColor = OrataTheme.colors.inverseSurface,
                            modifier = Modifier.weight(1f)
                        )
                        ColorSchemaItem(
                            text = "Inverse Primary",
                            backgroundColor = OrataTheme.colors.inversePrimary,
                            contentColor = OrataTheme.colors.onPrimaryContainer,
                            modifier = Modifier.weight(1f)
                        )

                        ColorSchemaItem(
                            text = "Scrim",
                            backgroundColor = OrataTheme.colors.scrim,
                            contentColor = Color.White,
                            modifier = Modifier.weight(1f)
                                .padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorSchemaItem(
    text: String = "Surface",
    backgroundColor: Color = OrataTheme.colors.surface,
    contentColor: Color = OrataTheme.colors.onSurface,
    modifier: Modifier = Modifier
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = text,
            style = OrataTheme.typography.bodyMedium(),
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        )
    }
}