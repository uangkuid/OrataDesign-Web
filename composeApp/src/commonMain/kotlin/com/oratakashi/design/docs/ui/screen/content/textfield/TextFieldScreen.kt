package com.oratakashi.design.docs.ui.screen.content.textfield

import androidx.compose.foundation.layout.Arrangement
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
import com.oratakashi.design.component.textfield.OraTextFieldState
import com.oratakashi.design.docs.navigation.page.TextFieldNavigation
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeData
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeTable
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.component_preview.PreviewType
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.component.switches.Switch
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.docs.ui.templates.textfield.TextField
import com.oratakashi.design.docs.ui.templates.textfield.TextFieldConfig
import com.oratakashi.design.docs.ui.templates.textfield.variant.default.DefaultTextField
import com.oratakashi.design.docs.ui.templates.textfield.variant.disabled.DisabledTextField
import com.oratakashi.design.docs.ui.templates.textfield.variant.error.ErrorTextField
import com.oratakashi.design.docs.ui.templates.textfield.variant.locked.LockedTextField
import com.oratakashi.design.docs.ui.templates.textfield.variant.success.SuccessTextField
import com.oratakashi.design.component.textfield.OraTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
    var textFieldData by remember {
        mutableStateOf(
            TextFieldConfig(
                value = "",
                placeholder = "Enter text here",
                label = "Text Field Label",
                state = OraTextFieldState.Default(),
                enabled = true,
                singleLine = true,
                showLeadingIcon = true,
                showTrailingIcon = true
            )
        )
    }
    
    val data: List<AttributeData> = listOf(
        AttributeData(
            name = "value",
            description = "The current text value of the text field",
            required = true,
            type = "string",
            control = {
                OraTextField(
                    value = textFieldData.value,
                    onValueChange = {
                        textFieldData = textFieldData.copy(value = it)
                    },
                    state = OraTextFieldState.Default(),
                    placeholder = "Enter value",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "placeholder",
            description = "The placeholder text to display when the field is empty",
            required = false,
            type = "string",
            control = {
                OraTextField(
                    value = textFieldData.placeholder,
                    onValueChange = {
                        textFieldData = textFieldData.copy(placeholder = it)
                    },
                    state = OraTextFieldState.Default(),
                    placeholder = "Enter placeholder",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "label",
            description = "The label text for the text field",
            required = false,
            type = "string",
            control = {
                OraTextField(
                    value = textFieldData.label,
                    onValueChange = {
                        textFieldData = textFieldData.copy(label = it)
                    },
                    state = OraTextFieldState.Default(),
                    placeholder = "Enter label",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "state",
            description = "The current state of the text field (Default, Success, Error, or Locked)",
            required = false,
            type = "OraTextFieldState",
            control = {
                Switch(
                    checked = textFieldData.state is OraTextFieldState.Error,
                    onCheckedChange = {
                        textFieldData = textFieldData.copy(
                            state = if (it) {
                                OraTextFieldState.Error("This field has an error")
                            } else {
                                OraTextFieldState.Default()
                            }
                        )
                    }
                )
            }
        ),
        AttributeData(
            name = "enabled",
            description = "Controls whether the text field is enabled or disabled",
            required = false,
            type = "boolean",
            control = {
                Switch(
                    checked = textFieldData.enabled,
                    onCheckedChange = {
                        textFieldData = textFieldData.copy(enabled = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "singleLine",
            description = "Whether the text field should be single line or multiline",
            required = false,
            type = "boolean",
            control = {
                Switch(
                    checked = textFieldData.singleLine,
                    onCheckedChange = {
                        textFieldData = textFieldData.copy(singleLine = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "leadingIcon",
            description = "The optional icon to be displayed at the start of the text field",
            required = false,
            type = "Composable Function",
            control = {
                Switch(
                    checked = textFieldData.showLeadingIcon,
                    onCheckedChange = {
                        textFieldData = textFieldData.copy(showLeadingIcon = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "trailingIcon",
            description = "The optional icon to be displayed at the end of the text field",
            required = false,
            type = "Composable Function",
            control = {
                Switch(
                    checked = textFieldData.showTrailingIcon,
                    onCheckedChange = {
                        textFieldData = textFieldData.copy(showTrailingIcon = it)
                    }
                )
            }
        )
    )
    
    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = TextFieldNavigation
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
                        Text("The TextField component is a fundamental input element that allows users to enter and edit text. It provides a consistent and accessible way for users to input data within forms and interfaces.")

                        Text("Text fields support various states including default, success, error, disabled, and locked states. They can include labels, placeholders, helper text, and icons to improve usability and provide visual feedback. The component is designed to be flexible and can be configured for single-line or multi-line input depending on the use case.")

                        ComponentPreview(
                            navigation = TextFieldNavigation
                        ) {
                            TextField(
                                config = textFieldData,
                                modifier = Modifier.fillMaxWidth(),
                                onValueChange = {
                                    textFieldData = textFieldData.copy(value = it)
                                }
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
            
            item(
                key = "default_state"
            ) {
                ContentSection(
                    title = "Default State",
                    content = {
                        ComponentPreview(
                            navigation = TextFieldNavigation,
                            type = PreviewType.Variant("default")
                        ) {
                            DefaultTextField()
                        }
                    }
                )
            }
            
            item(
                key = "error_state"
            ) {
                ContentSection(
                    title = "Error State",
                    content = {
                        ComponentPreview(
                            navigation = TextFieldNavigation,
                            type = PreviewType.Variant("error")
                        ) {
                            ErrorTextField()
                        }
                    }
                )
            }
            
            item(
                key = "success_state"
            ) {
                ContentSection(
                    title = "Success State",
                    content = {
                        ComponentPreview(
                            navigation = TextFieldNavigation,
                            type = PreviewType.Variant("success")
                        ) {
                            SuccessTextField()
                        }
                    }
                )
            }
            
            item(
                key = "disabled_state"
            ) {
                ContentSection(
                    title = "Disabled State",
                    content = {
                        ComponentPreview(
                            navigation = TextFieldNavigation,
                            type = PreviewType.Variant("disabled")
                        ) {
                            DisabledTextField()
                        }
                    }
                )
            }
            
            item(
                key = "locked_state"
            ) {
                ContentSection(
                    title = "Locked State",
                    content = {
                        ComponentPreview(
                            navigation = TextFieldNavigation,
                            type = PreviewType.Variant("locked")
                        ) {
                            LockedTextField()
                        }
                    }
                )
            }
        }
    }
}
