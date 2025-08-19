package com.example.recipeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    paddingValues: PaddingValues, navController: NavHostController
) {
    val Teal = Color(0xFF03B5AA)
    val CreamBackground = Color(0xFFF9F6D0)
    val LabelColor = Color(0xFF444444)
    val FieldShape = RoundedCornerShape(12.dp)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Деталі рецепта",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад",
                            tint = Color.White
                        )
                    }
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
                AsyncImage(
                    model = "https://via.placeholder.com/400x250",
                    contentDescription = "Фото страви",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, FieldShape)
                )
            }

            item {
                Text("Назва страви", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = LabelColor)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Час приготування:", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = LabelColor)
                    Text(" 45 хв", color = LabelColor)
                }
            }

            item {
                Text("Категорії: Суп, Закуска", color = LabelColor)
            }

            item { Divider() }

            item {
                Text("Інгредієнти", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
            }

            item {
                Text("- Картопля — 2 шт.\n- Морква — 1 шт.\n- Сіль — за смаком", color = LabelColor)
            }

            item { Divider() }

            item {
                Text("Спосіб приготування", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
            }

            item {
                Text("1. Нарізати овочі.\n2. Варити 30 хв.\n3. Додати сіль.", color = LabelColor)
            }

            item { Divider() }

            item {
                Text("Примітки", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = LabelColor)
            }

            item {
                Text("Для готовки потрібна чудова дівчина - Юлія і її любов", color = LabelColor)
            }
        }
    }
}
