package com.oratakashi.design.docs.ui.component.color_schema

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.oratakashi.design.docs.ui.component.color_schema.content.ColorSchemaFooter
import com.oratakashi.design.docs.ui.component.color_schema.content.ColorSetColumn
import com.oratakashi.design.docs.ui.component.color_schema.content.ColorSetSection
import com.oratakashi.design.docs.ui.component.color_schema.content.InverseColorsSection
import com.oratakashi.design.docs.ui.component.color_schema.content.SurfaceColorsSection
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
                .defaultMinSize(minHeight = 1000.dp)
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 1000.dp)
            ) {
                val isWideScreen = maxWidth > 550.dp

                if (isWideScreen) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 1000.dp)
                    ) {
                        val (rowNormal, rowAccent, rowSurface, footer) = createRefs()

                        ColorSchemaFooter(
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
                            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Primary",
                                        OrataTheme.colors.primary,
                                        OrataTheme.colors.onPrimary,
                                        OrataTheme.colors.primaryContainer,
                                        OrataTheme.colors.onPrimaryContainer
                                    )
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(start = 4.dp)) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Secondary",
                                        OrataTheme.colors.secondary,
                                        OrataTheme.colors.onSecondary,
                                        OrataTheme.colors.secondaryContainer,
                                        OrataTheme.colors.onSecondaryContainer
                                    )
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(start = 4.dp)) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Tertiary",
                                        OrataTheme.colors.tertiary,
                                        OrataTheme.colors.onTertiary,
                                        OrataTheme.colors.tertiaryContainer,
                                        OrataTheme.colors.onTertiaryContainer
                                    )
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Error",
                                        OrataTheme.colors.error,
                                        OrataTheme.colors.onError,
                                        OrataTheme.colors.errorContainer,
                                        OrataTheme.colors.onErrorContainer
                                    )
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
                            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Info",
                                        OrataTheme.colors.info,
                                        OrataTheme.colors.onInfo,
                                        OrataTheme.colors.infoContainer,
                                        OrataTheme.colors.onInfoContainer
                                    )
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(start = 4.dp)) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Warning",
                                        OrataTheme.colors.warning,
                                        OrataTheme.colors.onWarning,
                                        OrataTheme.colors.warningContainer,
                                        OrataTheme.colors.onWarningContainer
                                    )
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(start = 4.dp)) {
                                ColorSetColumn(
                                    ColorSet(
                                        "Success",
                                        OrataTheme.colors.success,
                                        OrataTheme.colors.onSuccess,
                                        OrataTheme.colors.successContainer,
                                        OrataTheme.colors.onSuccessContainer
                                    )
                                )
                            }
                            Box(modifier = Modifier.weight(1f).padding(start = 16.dp))
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
                            Column(modifier = Modifier.weight(3f).fillMaxHeight()) {
                                SurfaceColorsSection()
                            }
                            Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                                InverseColorsSection()
                            }
                        }
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ColorSetSection(
                            ColorSet(
                                "Primary",
                                OrataTheme.colors.primary,
                                OrataTheme.colors.onPrimary,
                                OrataTheme.colors.primaryContainer,
                                OrataTheme.colors.onPrimaryContainer
                            ),
                            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
                        )

                        ColorSetSection(
                            ColorSet(
                                "Secondary",
                                OrataTheme.colors.secondary,
                                OrataTheme.colors.onSecondary,
                                OrataTheme.colors.secondaryContainer,
                                OrataTheme.colors.onSecondaryContainer
                            ),
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        )

                        ColorSetSection(
                            ColorSet(
                                "Tertiary",
                                OrataTheme.colors.tertiary,
                                OrataTheme.colors.onTertiary,
                                OrataTheme.colors.tertiaryContainer,
                                OrataTheme.colors.onTertiaryContainer
                            ),
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        )

                        ColorSetSection(
                            ColorSet(
                                "Error",
                                OrataTheme.colors.error,
                                OrataTheme.colors.onError,
                                OrataTheme.colors.errorContainer,
                                OrataTheme.colors.onErrorContainer
                            ),
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        )

                        ColorSetSection(
                            ColorSet(
                                "Info",
                                OrataTheme.colors.info,
                                OrataTheme.colors.onInfo,
                                OrataTheme.colors.infoContainer,
                                OrataTheme.colors.onInfoContainer
                            ),
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        )

                        ColorSetSection(
                            ColorSet(
                                "Warning",
                                OrataTheme.colors.warning,
                                OrataTheme.colors.onWarning,
                                OrataTheme.colors.warningContainer,
                                OrataTheme.colors.onWarningContainer
                            ),
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        )

                        ColorSetSection(
                            ColorSet(
                                "Success",
                                OrataTheme.colors.success,
                                OrataTheme.colors.onSuccess,
                                OrataTheme.colors.successContainer,
                                OrataTheme.colors.onSuccessContainer
                            ),
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp)
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 250.dp)
                        ) {
                            SurfaceColorsSection()
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp)
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 250.dp)
                        ) {
                            InverseColorsSection()
                        }

                        ColorSchemaFooter(
                            modifier = Modifier
                                .padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    bottom = 24.dp
                                )
                        )
                    }
                }
            }
        }
    }
}
