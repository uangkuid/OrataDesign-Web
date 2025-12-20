package com.oratakashi.design.docs.ui.screen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.oratakashi.design.app.navigation.contract.BaseNavigation
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun <T : BaseNavigation> DetailContent(
    navigation: T? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    showBack: Boolean = false,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        snackbarHost = snackbarHost,
        modifier = modifier.then(
            if (scrollBehavior != null) {
                Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            } else {
                Modifier
            }
        ),
        topBar = {
            LargeFlexibleTopAppBar(
                title = {
                    Text(
                        navigation?.title
                            ?.replace("com.oratakashi.design.docs.navigation.", "")
                            ?.replace("Navigation", "")
                            ?.replace("List", "").orEmpty(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = OrataTheme.typography.displaySmall(),
                        color = OrataTheme.colors.onSurface
                    )
                },
                navigationIcon = {
                    if (showBack) {
                        IconButton(
                            onClick = onBackClick,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = OrataTheme.colors.onSurfaceVariant
                            ),
                            modifier = Modifier
                                .padding(end = 8.dp)
                        ) {
                            Icon(
                                painter = rememberVectorPainter(FeatherIcons.ArrowLeft),
                                contentDescription = null
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content =  { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content.invoke()
            }
        }
    )
}