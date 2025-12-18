package com.oratakashi.design.docs.ui.component.content_section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.foundation.OrataTheme

@Composable
fun ContentSection(
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (title != null) {
            Text(
                text = title,
                style = OrataTheme.typography.headlineSmall(),
            )
        }

        content.invoke(this)
    }
}