package com.oratakashi.design.docs.ui.screen.content.anchortext

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
import com.oratakashi.design.component.textfield.OraTextField
import com.oratakashi.design.component.textfield.OraTextFieldState
import com.oratakashi.design.docs.navigation.page.AnchorTextNavigation
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeData
import com.oratakashi.design.docs.ui.component.attribute_table.AttributeTable
import com.oratakashi.design.docs.ui.component.component_preview.ComponentPreview
import com.oratakashi.design.docs.ui.component.component_preview.PreviewType
import com.oratakashi.design.docs.ui.component.content_section.ContentSection
import com.oratakashi.design.docs.ui.component.switches.Switch
import com.oratakashi.design.docs.ui.screen.content.DetailContent
import com.oratakashi.design.docs.ui.templates.anchortext.AnchorText
import com.oratakashi.design.docs.ui.templates.anchortext.AnchorTextConfig
import com.oratakashi.design.docs.ui.templates.anchortext.variant.large.LargeAnchorText
import com.oratakashi.design.docs.ui.templates.anchortext.variant.medium.MediumAnchorText
import com.oratakashi.design.docs.ui.templates.anchortext.variant.small.SmallAnchorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnchorTextScreen(
    onBackClick: () -> Unit = {},
    showBack: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var anchorTextData by remember {
        mutableStateOf(
            AnchorTextConfig(
                text = "Anchor Text",
                isEnable = true,
                showUnderline = false,
                showIconLeft = true,
                showRightIcon = true
            ),
        )
    }

    val data: List<AttributeData> = listOf(
        AttributeData(
            name = "name",
            description = "The text of the anchor text",
            required = true,
            type =  "string",
            control = {
                OraTextField(
                    value = anchorTextData.text,
                    onValueChange = {
                        anchorTextData = anchorTextData.copy(text = it)
                    },
                    state = if (anchorTextData.text.isEmpty()) {
                        OraTextFieldState.Error("This field is required")
                    } else {
                        OraTextFieldState.Default()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        AttributeData(
            name = "enabled",
            description = "Controls the enabled state of the anchor text",
            required = false,
            type =  "Boolean",
            control = {
                Switch(
                    checked = anchorTextData.isEnable,
                    onCheckedChange = {
                        anchorTextData = anchorTextData.copy(isEnable = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "iconLeft",
            description = "The icon to be displayed on the left side of the text",
            required = false,
            type =  "Composable Function",
            control = {
                Switch(
                    checked = anchorTextData.showIconLeft,
                    onCheckedChange = {
                        anchorTextData = anchorTextData.copy(showIconLeft = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "iconRight",
            description = "The icon to be displayed on the right side of the text",
            required = false,
            type =  "Composable Function",
            control = {
                Switch(
                    checked = anchorTextData.showRightIcon,
                    onCheckedChange = {
                        anchorTextData = anchorTextData.copy(showRightIcon = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "underline",
            description = "Controls whether the anchor text is underlined",
            required = false,
            type =  "Composable Function",
            control = {
                Switch(
                    checked = anchorTextData.showUnderline,
                    onCheckedChange = {
                        anchorTextData = anchorTextData.copy(showUnderline = it)
                    }
                )
            }
        ),
        AttributeData(
            name = "onClick",
            description = "Callback Whether to anchor text clicked",
            required = false,
            type =  "Lambda Function",
            control = {
            }
        ),
        AttributeData(
            name = "size",
            description = "The size to be used for this anchor text",
            required = false,
            type =  "OraAnchorTextSize",
            control = {
            }
        )
    )

    DetailContent(
        scrollBehavior = scrollBehavior,
        onBackClick = onBackClick,
        showBack = showBack,
        navigation = AnchorTextNavigation
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
                        Text("Anchor text refers to clickable text that functions as a hyperlink to another page, section, or external website. It provides users with a clear and descriptive indication of the destination or action associated with the link.")

                        Text("Well-crafted anchor text improves usability and accessibility by setting accurate expectations and making navigation more intuitive, especially for assistive technologies and screen readers.")

                        ComponentPreview(
                            navigation = AnchorTextNavigation
                        ) {
                            AnchorText(anchorTextData)
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
                key = "small_variant"
            ) {
                ContentSection(
                    title = "Small Variant",
                    content = {
                        ComponentPreview(
                            navigation = AnchorTextNavigation,
                            type = PreviewType.Variant("small")
                        ) {
                            SmallAnchorText()
                        }
                    }
                )
            }

            item(
                key = "medium_variant"
            ) {
                ContentSection(
                    title = "Medium Variant",
                    content = {
                        ComponentPreview(
                            navigation = AnchorTextNavigation,
                            type = PreviewType.Variant("medium")
                        ) {
                            MediumAnchorText()
                        }
                    }
                )
            }

            item(
                key = "large_variant"
            ) {
                ContentSection(
                    title = "Large Variant",
                    content = {
                        ComponentPreview(
                            navigation = AnchorTextNavigation,
                            type = PreviewType.Variant("large")
                        ) {
                            LargeAnchorText()
                        }
                    }
                )
            }
        }
    }
}
