package com.example.recipeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults

data class Ingredient(
    val name: String = "",
    val quantity: String = "",
    val unit: String = ""
)

val unitOptions = listOf("г", "мл", "мг", "шт.", "ст. ложка", "ч. ложка", "склянка", "долонь", "дрібка", "інше")
val categoryOptions = listOf("Суп", "Основне", "Салат", "Закуска", "Напій", "Десерт", "Випічка", "Соус", "Сніданок", "Інше")

val Teal = Color(0xFF03B5AA)
val DarkTeal = Color(0xFF256E6A)
val CreamBackground = Color(0xFFF9F6D0)
val FieldShape = RoundedCornerShape(12.dp)
val LabelColor = Color(0xFF444444)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(paddingValues: PaddingValues) {
    var recipeTitle by remember { mutableStateOf("") }
    var preparation by remember { mutableStateOf(TextFieldValue()) }
    val selectedCategories = remember { mutableStateListOf<String>() }
    var cookTime by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf(mutableListOf(Ingredient(), Ingredient())) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Додати рецепт",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Teal)
            )
        },
        containerColor = CreamBackground
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                Text("Назва страви", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = recipeTitle,
                    onValueChange = { recipeTitle = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Вкажіть назву страви", color = LabelColor.copy(alpha = 0.5f)) },
                    colors = fieldColors(),
                    shape = FieldShape
                )
            }

            item {
                Text("Інгредієнти", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
            }

            itemsIndexed(ingredients) { index, ingredient ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    OutlinedTextField(
                        value = ingredient.name,
                        onValueChange = { newValue ->
                            ingredients = ingredients.toMutableList().also {
                                it[index] = it[index].copy(name = newValue)
                            }
                        },
                        modifier = Modifier.weight(if (ingredients.size > 2) 0.6f else 1f),
                        placeholder = { Text("Назва", color = LabelColor.copy(alpha = 0.5f)) },
                        singleLine = true,
                        colors = fieldColors(),
                        shape = FieldShape
                    )

                    if (ingredients.size > 2) {
                        IconButton(
                            onClick = {
                                ingredients = ingredients.toMutableList().also {
                                    it.removeAt(index)
                                }
                            }
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Видалити", tint = DarkTeal)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = ingredient.quantity,
                        onValueChange = { newValue ->
                            ingredients = ingredients.toMutableList().also {
                                it[index] = it[index].copy(quantity = newValue)
                            }
                        },
                        modifier = Modifier.weight(0.26f),
                        placeholder = { Text("К-сть", color = LabelColor.copy(alpha = 0.5f)) },
                        singleLine = true,
                        colors = fieldColors(),
                        shape = FieldShape
                    )

                    var expanded by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.weight(0.34f)) {
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            OutlinedTextField(
                                value = ingredient.unit,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                                },
                                modifier = Modifier.menuAnchor().fillMaxWidth().defaultMinSize(minHeight = 56.dp),
                                placeholder = { Text("Міра", color = LabelColor.copy(alpha = 0.5f)) },
                                singleLine = true,
                                colors = fieldColors(),
                                shape = FieldShape
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(Color.White, FieldShape)
                            ) {
                                unitOptions.forEach { unit ->
                                    DropdownMenuItem(
                                        text = { Text(unit, color = LabelColor) },
                                        onClick = {
                                            ingredients = ingredients.toMutableList().also {
                                                it[index] = it[index].copy(unit = unit)
                                            }
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    if (ingredients.size > 2) {
                        Spacer(modifier = Modifier.weight(0.1f))
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        ingredients = ingredients.toMutableList().apply { add(Ingredient()) }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Додати інгредієнт", color = Color.White)
                }
            }

            item {
                Text("Спосіб приготування", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = preparation,
                    onValueChange = { preparation = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp, max = 400.dp),
                    placeholder = { Text("Опис кроків приготування...", color = LabelColor.copy(alpha = 0.5f)) },
                    colors = fieldColors(),
                    shape = FieldShape,
                    singleLine = false,
                    maxLines = Int.MAX_VALUE
                )
            }

            item {
                Text("Категорії страви", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))

                var expanded by remember { mutableStateOf(false) }
                val selectedCategories = remember { mutableStateListOf<String>() }

                Column {
                    OutlinedButton(
                        onClick = { expanded = !expanded },
                        shape = FieldShape,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Teal),
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
                                .background(Color.White, FieldShape)
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
                                    Text(item, color = LabelColor)
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Button(
                                onClick = { expanded = false },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = Teal),
                                shape = FieldShape
                            ) {
                                Text("Готово", color = Color.White)
                            }
                        }
                    }
                }
            }

            item {
                Text("Час приготування (хв)", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = cookTime,
                    onValueChange = { cookTime = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Вкажіть час", color = LabelColor.copy(alpha = 0.5f)) },
                    colors = fieldColors(),
                    shape = FieldShape
                )
            }

            item {
                Button(
                    onClick = { /* TODO: вибрати фото */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Фото страви", color = Color.White)
                }
            }

            item {
                Text("Примітки (опціонально)", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Наприклад поради/особливості", color = LabelColor.copy(alpha = 0.5f)) },
                    colors = fieldColors(),
                    shape = FieldShape
                )
            }

            item {
                Button(
                    onClick = { /* TODO: зберегти як чернетку */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Зберегти як чернетку", color = Color.White)
                }
            }

            item {
                Button(
                    onClick = { /* TODO: зберегти рецепт */ },
                    colors = ButtonDefaults.buttonColors(containerColor = DarkTeal),
                    shape = FieldShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Зберегти рецепт", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun fieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White,
    focusedTextColor = LabelColor,
    unfocusedTextColor = LabelColor,
    cursorColor = LabelColor
)
