package com.oratakashi.design.docs.ui.screen.content.button

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
import com.oratakashi.design.component.button.OraButtonColors
import com.oratakashi.design.component.button.OraButtonSize
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.navigation.page.ButtonNavigation
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeData
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeTable
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.component_preview.PreviewType
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.component.spinner.Spinner
import com.oratakashi.design.docs.ui.component.switches.Switch
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.docs.ui.templates.anchortext.AnchorText
import com.oratakashi.design.docs.ui.templates.button.Button
import com.oratakashi.design.docs.ui.templates.button.ButtonConfig
import com.oratakashi.design.docs.ui.templates.button.variant.outline.OutlineButton
import com.oratakashi.design.docs.ui.templates.button.variant.primary.PrimaryButton
import com.oratakashi.design.docs.ui.templates.button.variant.tonal.TonalButton
import com.oratakashi.design.docs.ui.templates.button.variant.transaparent.TransparentButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var buttonData by remember {
        mutableStateOf(
            ButtonConfig(
                label = "Button",
                isEnabled = true,
                isLoading = false,
                showIconLeft = true,
                showRightIcon = true,
                size = OraButtonSize.Medium
            )
        )
    }

    val data: List<AttributeData> = listOf(
        AttributeData(
            name = "label",
            description = "The text to be displayed inside the button",
            required = true,
            type =  "string",
            control = {
                OraTextField(
                    value = buttonData.label,
                    onValueChange = {
                        buttonData = buttonData.copy(label = it)
                    },
                    state = if (buttonData.label.isEmpty()) {
                        OraTextFieldState.Error("This field is required")
                    } else {
                        OraTextFieldState.Default()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "size",
            description = "The size configuration for this button",
            required = true,
            type =  "string",
            control = {
                Spinner(
                    OraButtonSize.entries.map { it.name },
                    selected = buttonData.size.name,
                    onItemSelected = {
                        buttonData = buttonData.copy(
                            size = OraButtonSize.valueOf(it)
                        )

                    }
                )
            }
        ),
        AttributeData(
            name = "enabled",
            description = "Controls the enabled state of the button",
            required = true,
            type =  "boolean",
            control = {
                Switch(
                    checked = buttonData.isEnabled,
                    onCheckedChange = {
                        buttonData = buttonData.copy(isEnabled = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "loading",
            description = "Controls the loading state showing a progress indicator",
            required = true,
            type =  "boolean",
            control = {
                Switch(
                    checked = buttonData.isLoading,
                    onCheckedChange = {
                        buttonData = buttonData.copy(isLoading = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "iconLeft",
            description = "The optional icon to be displayed on the left side",
            required = true,
            type =  "boolean",
            control = {
                Switch(
                    checked = buttonData.showIconLeft,
                    onCheckedChange = {
                        buttonData = buttonData.copy(showIconLeft = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "iconRight",
            description = "The optional icon to be displayed on the right side",
            required = true,
            type =  "boolean",
            control = {
                Switch(
                    checked = buttonData.showRightIcon,
                    onCheckedChange = {
                        buttonData = buttonData.copy(showRightIcon = it)
                    }
                )
            }
        )
    )


    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = ButtonNavigation
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
                        Text("Buttons are interactive elements used to initiate actions or direct users to internal or external links. They serve as clear call-to-action components within the interface.")

                        Text("Buttons typically contain concise and descriptive labels, and may optionally include icons to reinforce meaning and improve visual clarity. When designed and used consistently, buttons help guide user behavior and make interactions more intuitive and efficient.")

                        ComponentPreview(
                            navigation = ButtonNavigation
                        ) {
                            Button(config = buttonData)
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
                key = "primary_button"
            ) {
                ContentSection(
                    title = "Primary Button Variant",
                    content = {
                        ComponentPreview(
                            navigation = ButtonNavigation,
                            type = PreviewType.Variant("primary")
                        ) {
                            PrimaryButton()
                        }
                    }
                )
            }

            item(
                key = "tonal_button"
            ) {
                ContentSection(
                    title = "Tonal Button Variant",
                    content = {
                        ComponentPreview(
                            navigation = ButtonNavigation,
                            type = PreviewType.Variant("tonal")
                        ) {
                            TonalButton()
                        }
                    }
                )
            }

            item(
                key = "outline_button"
            ) {
                ContentSection(
                    title = "Outline Button Variant",
                    content = {
                        ComponentPreview(
                            navigation = ButtonNavigation,
                            type = PreviewType.Variant("outline")
                        ) {
                            OutlineButton()
                        }
                    }
                )
            }

            item(
                key = "transparent_button"
            ) {
                ContentSection(
                    title = "Transparent Button Variant",
                    content = {
                        ComponentPreview(
                            navigation = ButtonNavigation,
                            type = PreviewType.Variant("transparent")
                        ) {
                            TransparentButton()
                        }
                    }
                )
            }
        }
    }
}
