package com.oratakashi.design.docs.ui.component.component_preview.code_editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.icons.LogoIcon
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Moon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeSidebar(
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

                            }
                        ) {
                            Icon(
                                imageVector = FeatherIcons.Moon,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrataTheme.colors.surfaceContainer
                )
            )
        },
        containerColor = OrataTheme.colors.surfaceContainer,
        modifier = modifier
    ) {

    }
}