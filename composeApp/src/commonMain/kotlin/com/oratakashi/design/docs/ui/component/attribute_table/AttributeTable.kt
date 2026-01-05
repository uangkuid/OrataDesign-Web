package com.oratakashi.design.docs.ui.component.attribute_table

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable

@Composable
fun AttributeTable(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val isWideScreen = maxWidth > 1200.dp

        val column = mutableListOf(
            DataColumn {
                Text("Name")
            },
            DataColumn {
                Text("Description")
            },
            DataColumn {
                Text("Default")
            },
            DataColumn {
                Text("Required")
            },
        )

        if (isWideScreen) {
            column += DataColumn {
                Text("Control")
            }
        }

        DataTable(
            columns = column,
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}