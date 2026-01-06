package com.oratakashi.design.docs.ui.component.attribute_table

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oratakashi.design.docs.ui.component.code.InlineCode
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable
import dev.snipme.highlights.model.SyntaxLanguage

@Composable
fun AttributeTable(
    data: List<AttributeData> = emptyList(),
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val isWideScreen = maxWidth > 1200.dp

        val column = mutableListOf(
            DataColumn {
                Text(
                    text = "Name",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            },
            DataColumn {
                Text(
                    text = "Description",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            },
            DataColumn {
                Text(
                    text = "Default",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            },
            DataColumn {
                Text(
                    text = "Required",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            },
        )

        if (isWideScreen) {
            column += DataColumn {
                Text(
                    text = "Control",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
        }

        DataTable(
            columns = column,
            modifier = Modifier.fillMaxWidth(),
            rowHeight = Dp.Unspecified,
        ) {
            data.forEach {
                row {
                    cell {
                        InlineCode(
                            code = it.name,
                            language = SyntaxLanguage.KOTLIN,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }
                    cell {
                        Text(
                            text = it.description,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }

                    cell {
                        Text(
                            text = it.defaultValue ?: "",
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }
                    cell {
                        Text(
                            text = it.required.toString(),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }

                    if (isWideScreen) {
                        cell {
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .width(200.dp)
                            ) {
                                it.control()
                            }
                        }
                    }
                }
            }
        }
    }
}