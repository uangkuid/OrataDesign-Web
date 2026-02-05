package com.oratakashi.design.docs.ui.screen.content.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oratakashi.design.component.button.OraButton
import com.oratakashi.design.component.snackbar.OraSnackbar
import com.oratakashi.design.component.snackbar.OraSnackbarHost
import com.oratakashi.design.component.snackbar.OraSnackbarHostState
import com.oratakashi.design.component.snackbar.OraSnackbarSize
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import compose.icons.FeatherIcons
import compose.icons.feathericons.Info
import kotlinx.coroutines.launch
import com.oratakashi.design.docs.navigation.page.SnackbarNavigation
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeData
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeTable
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.component_preview.PreviewType
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.component.spinner.Spinner
import com.oratakashi.design.docs.ui.component.switches.Switch
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.docs.ui.templates.snackbar.Snackbar
import com.oratakashi.design.docs.ui.templates.snackbar.SnackbarConfig
import com.oratakashi.design.docs.ui.templates.snackbar.variant.error.ErrorSnackbar
import com.oratakashi.design.docs.ui.templates.snackbar.variant.information.InformationSnackbar
import com.oratakashi.design.docs.ui.templates.snackbar.variant.normal.NormalSnackbar
import com.oratakashi.design.docs.ui.templates.snackbar.variant.success.SuccessSnackbar
import com.oratakashi.design.docs.ui.templates.snackbar.variant.tertiary.TertiarySnackbar
import com.oratakashi.design.docs.ui.templates.snackbar.variant.warning.WarningSnackbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val snackbarHostState = remember { OraSnackbarHostState() }
    val scope = rememberCoroutineScope()

    var snackbarData by remember {
        mutableStateOf(
            SnackbarConfig(
                title = "This is title",
                description = "This is description",
                actionLabel = "",
                includeOnClose = false,
                includeIcon = true,
                size = OraSnackbarSize.Large
            )
        )
    }

    val data: List<AttributeData> = listOf(
        AttributeData(
            name = "title",
            description = "The title text of the snackbar",
            required = true,
            type = "string",
            control = {
                OraTextField(
                    value = snackbarData.title,
                    onValueChange = {
                        snackbarData = snackbarData.copy(title = it)
                    },
                    state = if (snackbarData.title.isEmpty()) {
                        OraTextFieldState.Error("This field is required")
                    } else {
                        OraTextFieldState.Default()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "description",
            description = "The description text of the snackbar",
            required = false,
            type = "string",
            control = {
                OraTextField(
                    value = snackbarData.description,
                    onValueChange = { snackbarData = snackbarData.copy(description = it) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false,
                    placeholder = "Input description here"
                )
            }
        ),
        AttributeData(
            name = "actionLabel",
            description = "The optional action label text",
            required = false,
            type = "string",
            control = {
                OraTextField(
                    value = snackbarData.actionLabel,
                    onValueChange = { snackbarData = snackbarData.copy(actionLabel = it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Input action label here"
                )
            }
        ),
        AttributeData(
            name = "showCloseIcon",
            description = "Whether to show the close icon",
            required = false,
            type = "boolean",
            control = {
                Switch(
                    checked = snackbarData.includeOnClose,
                    onCheckedChange = {
                        snackbarData = snackbarData.copy(includeOnClose = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "onClose",
            description = "Callback invoked when the close icon is clicked",
            required = false,
            type = "Lambda Function",
            control = {
            }
        ),
        AttributeData(
            name = "icon",
            description = "The optional icon to be displayed",
            required = false,
            type = "Composable Function",
            control = {
                Switch(
                    checked = snackbarData.includeIcon,
                    onCheckedChange = {
                        snackbarData = snackbarData.copy(includeIcon = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "size",
            description = "The size configuration for this snackbar",
            required = false,
            type = "OraSnackbarSize",
            control = {
                Spinner(
                    OraSnackbarSize.entries.map { it.name },
                    selected = snackbarData.size.name,
                    onItemSelected = {
                        snackbarData = snackbarData.copy(
                            size = OraSnackbarSize.valueOf(it)
                        )
                    }
                )
            }
        )
    )

    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = SnackbarNavigation
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
                        Text("The Snackbar component is used to provide brief feedback about an operation through a message at the bottom of the screen. Snackbars inform users of a process that an app has performed or will perform.")

                        Text("Snackbars appear temporarily, towards the bottom of the screen. They shouldn't interrupt the user experience, and they don't require user input to disappear. They can contain an action or be dismissed automatically.")

                        ComponentPreview(
                            navigation = SnackbarNavigation
                        ) {
                            Scaffold(
                                snackbarHost = {
                                    OraSnackbarHost(
                                        hostState = snackbarHostState,
                                        snackbar = {
                                            OraSnackbar(it)
                                        }
                                    )
                                }
                            ) { paddingValues ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(paddingValues),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Snackbar(snackbarData) {
                                        // Close callback
                                    }

                                    OraButton(
                                        onClick = {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    title = snackbarData.title,
                                                    message = snackbarData.description.ifEmpty { null },
                                                    icon = if (snackbarData.includeIcon) FeatherIcons.Info else null,
                                                    actionLabel = snackbarData.actionLabel.ifEmpty { null },
                                                    withDismissAction = snackbarData.includeOnClose,
                                                    size = snackbarData.size
                                                )
                                            }
                                        },
                                        label = "Show Snackbar",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
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

            item(
                key = "normal_variant"
            ) {
                ContentSection(
                    title = "Normal Snackbar Variant",
                    content = {
                        ComponentPreview(
                            navigation = SnackbarNavigation,
                            type = PreviewType.Variant("normal")
                        ) {
                            NormalSnackbar()
                        }
                    }
                )
            }

            item(
                key = "tertiary_variant"
            ) {
                ContentSection(
                    title = "Tertiary Snackbar Variant",
                    content = {
                        ComponentPreview(
                            navigation = SnackbarNavigation,
                            type = PreviewType.Variant("tertiary")
                        ) {
                            TertiarySnackbar()
                        }
                    }
                )
            }

            item(
                key = "error_variant"
            ) {
                ContentSection(
                    title = "Error Snackbar Variant",
                    content = {
                        ComponentPreview(
                            navigation = SnackbarNavigation,
                            type = PreviewType.Variant("error")
                        ) {
                            ErrorSnackbar()
                        }
                    }
                )
            }

            item(
                key = "warning_variant"
            ) {
                ContentSection(
                    title = "Warning Snackbar Variant",
                    content = {
                        ComponentPreview(
                            navigation = SnackbarNavigation,
                            type = PreviewType.Variant("warning")
                        ) {
                            WarningSnackbar()
                        }
                    }
                )
            }

            item(
                key = "information_variant"
            ) {
                ContentSection(
                    title = "Information Snackbar Variant",
                    content = {
                        ComponentPreview(
                            navigation = SnackbarNavigation,
                            type = PreviewType.Variant("information")
                        ) {
                            InformationSnackbar()
                        }
                    }
                )
            }

            item(
                key = "success_variant"
            ) {
                ContentSection(
                    title = "Success Snackbar Variant",
                    content = {
                        ComponentPreview(
                            navigation = SnackbarNavigation,
                            type = PreviewType.Variant("success")
                        ) {
                            SuccessSnackbar()
                        }
                    }
                )
            }
        }
    }
}
