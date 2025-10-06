package com.example.recipeapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.recipeapp.ui.screens.FieldShape
import com.example.recipeapp.ui.screens.LabelColor
import com.example.recipeapp.ui.screens.fieldColors

val FieldShape = RoundedCornerShape(12.dp)
val LabelColor = Color(0xFF444444)

/**
 * Компонент пошуку
 * @param query - поточне значення рядка пошуку
 * @param onQueryChange - колбек при зміні тексту
 */
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = { newValue -> onQueryChange(newValue) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Пошук", color = LabelColor.copy(alpha = 0.5f)) },
        colors = fieldColors(),
        shape = FieldShape
    )
}
