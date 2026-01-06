package com.oratakashi.design.docs.ui.screen.content.alert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.component.alert.OraInfoAlert
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.docs.navigation.page.AlertNavigation
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeData
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeTable
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val data: List<AttributeData> = listOf(
        AttributeData(
            name = "name",
            description = "The title text of the alert",
            required = true,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "description",
            description = "The description text of the alert",
            required = false,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "visible",
            description = "Controls the visibility of the alert with fade animation",
            required = false,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "showCloseIcon",
            description = "Whether to show the close icon",
            required = false,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "onClose",
            description = "Callback Whether to show the close icon",
            required = false,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "action",
            description = "The optional action composable to be displayed",
            required = false,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "icon",
            description = "The optional icon to be displayed, defaults to info icon",
            required = false,
            defaultValue = "-",
            control = {
                var inputState by remember { mutableStateOf("") }
                OraTextField(
                    value = inputState,
                    onValueChange = { inputState = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    )
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = AlertNavigation
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            item(
                key = "preview"
            ) {
                ContentSection(
                    content = {
                        Text("The Alert component is used to present a temporary surface that offers a list of available actions or options. It is designed to display a brief message or notification to the user within the context of an existing activity, without requiring a full screen transition.")

                        Text("Alerts are typically used to communicate important information, request user confirmation, or present time-sensitive choices. By appearing as an overlay on top of the current interface, the Alert component captures user attention while maintaining continuity with the underlying content.")

                        ComponentPreview {
                            OraInfoAlert(
                                title = "Info",
                                description = "Lorem Ipsum is simply dummy text",
                            )
                        }
                    }
                )
            }

            item(
                key = "attributes"
            ) {
                ContentSection(
                    title = "Attributes",
                    content = {
                        AttributeTable(
                            data = data
                        )
                    }
                )
            }
        }
    }
}
