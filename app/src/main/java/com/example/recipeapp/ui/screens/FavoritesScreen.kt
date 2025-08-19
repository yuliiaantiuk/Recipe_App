package com.example.recipeapp.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(paddingValues: PaddingValues) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Список збережених рецептів") }) }
    ) {
        Text(
            "Тут буде список збережених рецептів",
            modifier = Modifier.padding(it) // 👈 it — це padding від Scaffold
        )
    }
}



