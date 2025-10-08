package com.example.recipeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.screens.DarkTeal
import com.example.recipeapp.ui.screens.Teal
import com.example.recipeapp.ui.screens.categoryOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList

@Composable
fun CheckboxList(selectedCategories: SnapshotStateList<String>){
    var expanded by remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = { expanded = !expanded },
        shape = com.example.recipeapp.ui.screens.FieldShape,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Teal)
    ) {
        val label = if (selectedCategories.isEmpty())
            "Оберіть категорії"
        else
            selectedCategories.joinToString(", ")

        Text(label, color = Color.White)
    }

    if (expanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, com.example.recipeapp.ui.screens.FieldShape)
                .padding(8.dp)
        ) {
            categoryOptions.forEach { item ->
                val isChecked = item in selectedCategories
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            if (checked) selectedCategories.add(item)
                            else selectedCategories.remove(item)
                        },
                        colors = CheckboxDefaults.colors(checkedColor = DarkTeal)
                    )
                    Text(item, color = com.example.recipeapp.ui.screens.LabelColor)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = { expanded = false },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Teal),
                shape = com.example.recipeapp.ui.screens.FieldShape
            ) {
                Text("Готово", color = Color.White)
            }
        }
    }
}