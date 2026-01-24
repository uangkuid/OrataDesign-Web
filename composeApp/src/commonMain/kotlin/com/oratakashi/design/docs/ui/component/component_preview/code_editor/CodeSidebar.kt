package com.oratakashi.design.docs.ui.component.component_preview.code_editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.oratakashi.design.docs.data.model.code_sidebar.TemplateContent
import com.oratakashi.design.docs.icons.LogoIcon
import com.oratakashi.design.docs.ui.component.sidebar.selectedOutline
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Moon
import compose.icons.feathericons.Sun

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeSidebar(
    darkMode: Boolean = true,
    fileList: List<TemplateContent> = emptyList(),
    selected: TemplateContent? = null,
    onSidebarClick: (TemplateContent) -> Unit = {},
    onDarkModeChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(end = 16.dp)
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
                            "Code",
                            style = OrataTheme.typography.titleLarge()
                        )

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = {
                                onDarkModeChange.invoke(!darkMode)
                            }
                        ) {
                            Icon(
                                imageVector = if (darkMode) FeatherIcons.Moon else FeatherIcons.Sun,
                                contentDescription = null
                            )
                        }
                    }
                },
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            )
        ) {
            item(key = "title") {
                Text(
                    text = "Files",
                    style = OrataTheme.typography.labelMedium(),
                    color = OrataTheme.colors.outline,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                )
            }


            items(
                items = fileList,
                key = { it.name.orEmpty() }
            ) {
                SidebarMenu(
                    data = it,
                    isSelected = it == selected,
                    onClick = {
                        onSidebarClick.invoke(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun SidebarMenu(
    data: TemplateContent,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val filetype = remember(data) { Filetype.from(data.fileType.orEmpty()) }
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
                    top = 10.dp,
                    bottom = 10.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            val (tvText, ivIcon) = createRefs()

            Icon(
                imageVector = filetype.icons(),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(ivIcon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)

                        width = Dimension.value(18.dp)
                        height = Dimension.value(18.dp)
                    }
            )

            Text(
                text = data.name.orEmpty(),
                style = OrataTheme.typography.bodyLarge(),
                color = OrataTheme.colors.onSurface,
                modifier = Modifier
                    .constrainAs(tvText) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(ivIcon.end, 8.dp)
                        end.linkTo(parent.end)

                        width = Dimension.fillToConstraints
                    }
            )
        }
    }
}