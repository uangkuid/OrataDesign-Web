package com.oratakashi.design.docs.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.oratakashi.design.component.button.OraTonalButton
import com.oratakashi.design.docs.icons.LogoIcon
import com.oratakashi.design.foundation.OrataTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Preview(widthDp = 1080, showBackground = true)
@Composable
fun HomeScreen(
    onClicked: () -> Unit = {},
    modifier: Modifier = Modifier.fillMaxSize()
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (ivLogo, tvTitle, tvDesc, btnCTA, tvFooter) = createRefs()

        Icon(
            imageVector = LogoIcon.icons(),
            contentDescription = null,
            tint = OrataTheme.colors.primary,
            modifier = Modifier
                .constrainAs(ivLogo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(tvTitle.start, 16.dp)

                    width = Dimension.value(64.dp)
                    height = Dimension.value(64.dp)
                }
        )

        Text(
            text = "Orata Design System",
            style = OrataTheme.typography.headlineLarge(),
            modifier = Modifier
                .constrainAs(tvTitle) {
                    top.linkTo(ivLogo.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    width = Dimension.wrapContent
                }
        )

        Text(
            text = "Modern Cross-Platform Design System for Kotlin Multiplatform",
            style = OrataTheme.typography.titleMedium(),
            color = OrataTheme.colors.onSurfaceVariant,
            modifier = Modifier
                .constrainAs(tvDesc) {
                    top.linkTo(tvTitle.bottom, 8.dp)
                    start.linkTo(tvTitle.start)
                    end.linkTo(parent.end, 24.dp)

                    width = Dimension.fillToConstraints
                }
        )

        OraTonalButton(
            label = "Get Started",
            iconRight = {
                Icon(FeatherIcons.ArrowRight, null)
            },
            onClick = onClicked,
            modifier = Modifier
                .constrainAs(btnCTA) {
                    top.linkTo(tvDesc.bottom, 24.dp)
                    start.linkTo(ivLogo.end, 16.dp)
                }
        )

        Text(
            text = "Copyright @${getYear()} Oratakashi",
            style = OrataTheme.typography.labelMedium(),
            color = OrataTheme.colors.outline,
            modifier = Modifier
                .constrainAs(tvFooter) {
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@OptIn(ExperimentalTime::class)
private fun getYear(): String {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.year.toString()
}