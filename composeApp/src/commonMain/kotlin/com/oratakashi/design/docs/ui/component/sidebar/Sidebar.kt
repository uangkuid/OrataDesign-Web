package com.oratakashi.design.docs.ui.component.sidebar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.oratakashi.design.app.navigation.contract.BaseNavigation
import com.oratakashi.design.docs.Config
import com.oratakashi.design.docs.icons.LogoIcon
import com.oratakashi.design.docs.models.sidebar.SidebarItem
import com.oratakashi.design.foundation.OrataTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sidebar(
    isDetailShow: Boolean = false,
    onSidebarClick: (BaseNavigation?) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = LogoIcon.icons(),
                            null,
                            tint = OrataTheme.colors.primary,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                        Text(
                            "Orata Design",
                            style = OrataTheme.typography.titleLarge()
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->

        val selected = remember {
            mutableStateOf(if (isDetailShow) Config.defaultSelectionSidebar else null)
        }

        val sidebarItem = remember { Config.sidebarItem }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {

            for (item in sidebarItem) {
                item(
                    key = item.title
                ) {
                    Text(
                        text = item.title,
                        style = OrataTheme.typography.labelMedium(),
                        color = OrataTheme.colors.outline,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }

                items(
                    items = item.item,
                    key = { it.label }
                ) {
                    SidebarMenu(
                        data = it,
                        isSelected = it == selected.value,
                        onClick = {
                            selected.value = it
                            onSidebarClick.invoke(it.navigation)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SidebarMenu(
    data: SidebarItem,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) OrataTheme.colors.surfaceContainerHigh else OrataTheme.colors.surface
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .selectedOutline(
                    outlineColor = if (isSelected) OrataTheme.colors.primary else OrataTheme.colors.surface,
                    outlineWidth = if (isSelected) 3.dp else 0.dp,
                    radius = if (isSelected) 8.dp else 0.dp
                )
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 24.dp,
                    end = 16.dp
                )
        ) {
            val (tvText) = createRefs()

            Text(
                text = data.label,
                style = OrataTheme.typography.bodyLarge(),
                color = OrataTheme.colors.onSurface,
                modifier = Modifier
                    .constrainAs(tvText) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                        width = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@Composable
private fun Modifier.selectedOutline(
    outlineColor: Color,
    outlineWidth: Dp,
    radius: Dp = 1.dp
) = drawBehind {
    val outlineWidthPx = outlineWidth.toPx()
    val radiusPx = radius.toPx()

    // Hitung tinggi outline (50% dari tinggi component)
    val outlineHeight = size.height * 0.5f
    // Mulai dari tengah, jadi offset Y adalah (tinggi total - tinggi outline) / 2
    val startY = (size.height - outlineHeight) / 2f

    // Gambar outline di sebelah kiri saja
    drawRoundRect(
        color = outlineColor,
        topLeft = Offset(0f, startY),
        size = Size(outlineWidthPx, outlineHeight),
        cornerRadius = CornerRadius(radiusPx, radiusPx),
        style = Fill
    )
}