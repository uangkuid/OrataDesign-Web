package com.oratakashi.design.docs.ui.component.colorschema

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
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
                .height(900.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (rowNormal, rowFix, rowSurface, footer) = createRefs()

                Text(
                    text = "Diagram of all Orata Design color roles, including optional add-on roles for surface colors and fixed accent colors",
                    modifier = Modifier.constrainAs(footer) {
                        bottom.linkTo(parent.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)

                        width = Dimension.fillToConstraints
                    }
                )

                FlowRow(
                    modifier = Modifier.constrainAs(rowNormal) {
                        top.linkTo(parent.top, 24.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                        bottom.linkTo(rowFix.top)
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
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
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
                }

                FlowRow(
                    modifier = Modifier.constrainAs(rowFix) {
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
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {

                    }
                }

                FlowRow(
                    modifier = Modifier.constrainAs(rowSurface) {
                        top.linkTo(rowFix.bottom, 24.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                        bottom.linkTo(footer.top, 16.dp)
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
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
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
            style = OrataTheme.typography.bodySmall(),
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        )
    }
}