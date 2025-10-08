package com.example.recipeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipeapp.ui.navigation.Screen
import com.example.recipeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(paddingValues: PaddingValues, navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val selectedCategories = remember { mutableStateListOf<String>() }

    val allRecipes = remember {
        mutableStateListOf(
            PublicRecipe(1, "Борщ", listOf("Суп"), null, true, likes = 12),
            PublicRecipe(2, "Сирники", listOf("Десерт", "Сніданок"), null, likes = 28),
            PublicRecipe(3, "Салат Цезар", listOf("Салат"), null, true, likes = 45),
            PublicRecipe(4, "Паста", listOf("Основне"), null, likes = 33)
        )
    }

    val filteredRecipes = allRecipes
        .filter { recipe ->
            recipe.isFavorite &&
                    recipe.title.contains(searchQuery, ignoreCase = true) &&
                    selectedCategories.all { cat -> cat in recipe.categories }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Обрані рецепти", fontWeight = FontWeight.Bold, color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Teal)
            )
        },
        containerColor = CreamBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize()
        ) {
            com.example.recipeapp.ui.components.SearchBar(
                query = searchQuery,
                onQueryChange = { newQuery -> searchQuery = newQuery }
            )

            Spacer(modifier = Modifier.height(12.dp))

//            var expanded by remember { mutableStateOf(false) }

            Column {
                com.example.recipeapp.ui.components.CheckboxList(selectedCategories = selectedCategories)
//                OutlinedButton(
//                    onClick = { expanded = !expanded },
//                    shape = FieldShape,
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(containerColor = Teal)
//                ) {
//                    val label = if (selectedCategories.isEmpty())
//                        "Оберіть категорії"
//                    else
//                        selectedCategories.joinToString(", ")
//
//                    Text(label, color = Color.White)
//                }
//
//                if (expanded) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Color.White, FieldShape)
//                            .padding(8.dp)
//                    ) {
//                        categoryOptions.forEach { item ->
//                            val isChecked = item in selectedCategories
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 4.dp)
//                            ) {
//                                Checkbox(
//                                    checked = isChecked,
//                                    onCheckedChange = { checked ->
//                                        if (checked) selectedCategories.add(item)
//                                        else selectedCategories.remove(item)
//                                    },
//                                    colors = CheckboxDefaults.colors(checkedColor = DarkTeal)
//                                )
//                                Text(item, color = LabelColor)
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(6.dp))
//                        Button(
//                            onClick = { expanded = false },
//                            modifier = Modifier.fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(containerColor = Teal),
//                            shape = FieldShape
//                        ) {
//                            Text("Готово", color = Color.White)
//                        }
//                    }
//                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (filteredRecipes.isEmpty()) {
                Text("Немає результатів", color = LabelColor)
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 100.dp, top = 12.dp)
                ) {
                    items(filteredRecipes) { recipe ->
                        Column(
                            modifier = Modifier
                                .background(Color.White, FieldShape)
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .clickable { navController.navigate(Screen.RecipeDetail.route) }
                                    .background(Color.LightGray, FieldShape)
                            ) {
                                AsyncImage(
                                    model = recipe.imageUrl ?: "https://via.placeholder.com/200",
                                    contentDescription = recipe.title,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                recipe.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = LabelColor,
                                modifier = Modifier.clickable { navController.navigate(Screen.RecipeDetail.route) }
                            )

                            Text(
                                recipe.categories.joinToString(", "),
                                fontSize = 12.sp,
                                color = LabelColor.copy(alpha = 0.7f)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = {
                                        val index = allRecipes.indexOfFirst { it.id == recipe.id }
                                        if (index != -1) {
                                            allRecipes[index] =
                                                allRecipes[index].copy(likes = allRecipes[index].likes + 1)
                                        }
                                    }) {
                                        Icon(Icons.Filled.Favorite, contentDescription = "Лайк", tint = Color.Red)
                                    }
                                    Text("${recipe.likes}", fontSize = 12.sp, color = LabelColor)
                                }

                                IconButton(onClick = {
                                    val index = allRecipes.indexOfFirst { it.id == recipe.id }
                                    if (index != -1) {
                                        allRecipes[index] =
                                            allRecipes[index].copy(isFavorite = !recipe.isFavorite)
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (recipe.isFavorite) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                                        contentDescription = "В обране",
                                        tint = DarkTeal
                                    )
                                }

                                IconButton(onClick = {
                                    // TODO: Android share intent
                                }) {
                                    Icon(Icons.Default.Share, contentDescription = "Поділитися", tint = LabelColor)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
