package com.example.recipeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.navigation.NavHostController
import androidx.compose.foundation.clickable
import com.example.recipeapp.ui.navigation.Screen

data class Recipe(
    val id: Int,
    val title: String,
    val categories: List<String>,
    val imageUrl: String?,
    var isFavorite: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(paddingValues: PaddingValues, navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val selectedCategories = remember { mutableStateListOf<String>() }

    val allRecipes = remember {
        mutableStateListOf(
            Recipe(1, "Борщ український", listOf("Суп"), null, isFavorite = true),
            Recipe(2, "Олів'є", listOf("Салат"), null),
            Recipe(3, "Млинці з сиром", listOf("Десерт", "Випічка"), null),
            Recipe(4, "Сирники", listOf("Сніданок", "Десерт"), null)
        )
    }

    val filteredRecipes = allRecipes
        .filter { recipe ->
            recipe.title.contains(searchQuery, ignoreCase = true) &&
                    selectedCategories.all { it in recipe.categories }
        }
        .sortedByDescending { it.isFavorite }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Мої рецепти",
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
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize()
                .background(CreamBackground),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                com.example.recipeapp.ui.components.SearchBar(
                    query = searchQuery,
                    onQueryChange = { newQuery -> searchQuery = newQuery }
                )
            }

            item {
                var expanded by remember { mutableStateOf(false) }

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

            if (filteredRecipes.isEmpty()) {
                item {
                    Text(
                        "Немає результатів за вибраними параметрами.",
                        color = LabelColor
                    )
                }
            } else {
                items(filteredRecipes) { recipe ->
                    RecipeListItem(
                        title = recipe.title,
                        categories = recipe.categories,
                        imageUrl = recipe.imageUrl,
                        isFavorite = recipe.isFavorite,
                        onToggleFavorite = {
                            val index = allRecipes.indexOfFirst { it.id == recipe.id }
                            if (index != -1) {
                                allRecipes[index] = allRecipes[index].copy(isFavorite = !recipe.isFavorite)
                            }
                        },
                        onEdit = { /* TODO */ },
                        onShare = { /* TODO */ },
                        onClick = {
                            navController.navigate(Screen.RecipeDetail.route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeListItem(
    title: String,
    categories: List<String>,
    imageUrl: String?,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    onEdit: () -> Unit,
    onShare: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = FieldShape)
            .padding(8.dp)
            .clip(FieldShape),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = imageUrl ?: "https://via.placeholder.com/100",
            contentDescription = "Фото рецепта",
            modifier = Modifier
                .size(80.dp)
                .clip(FieldShape)
                .background(Color.LightGray)
                .clickable { onClick() }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = LabelColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { onClick() }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = categories.joinToString(", "),
                    style = MaterialTheme.typography.bodySmall,
                    color = LabelColor,
                    modifier = Modifier.weight(1f)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    IconButton(onClick = onToggleFavorite) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                            contentDescription = "В обране",
                            tint = DarkTeal
                        )
                    }
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Редагувати", tint = LabelColor)
                    }
                    IconButton(onClick = onShare) {
                        Icon(Icons.Default.Share, contentDescription = "Поділитися", tint = LabelColor)
                    }
                }
            }
        }
    }
}