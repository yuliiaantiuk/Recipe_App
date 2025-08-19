package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.ui.components.BottomNavBar
import com.example.recipeapp.ui.navigation.AppNavHost
import com.example.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    }
                ) { paddingValues ->
                    AppNavHost(
                        navController = navController,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

